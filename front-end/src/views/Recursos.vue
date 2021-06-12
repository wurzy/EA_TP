<template>
  <div id="recursos"  >

    <v-container>
        <v-row align="center" style="margin-top:40px">
            <AddRec/>
            <v-col cols=2>
                <v-text-field @keydown.enter="search()" label="Filtro" v-model="filtro"></v-text-field>
            </v-col>
            <v-icon @click="search()"> mdi-magnify </v-icon>
            <v-col align="right">
                <v-btn @click="download()" color='#ff9966'> Download </v-btn>
            </v-col>
        </v-row>
    
        <v-row >
            <v-col cols="12" class="ma-0 pa-0"> 
              <v-data-table
                id="mytable"
                show-select
                v-model="selected"
                :headers="headers"
                :items="resources"
                :items-per-page="8"
                class="elevation-1"
                @click:row="handleClick"
                item-key="titulo">
                    <template v-slot:[`item.idResource`]="{ value}" >
                        <NewPub :value="value"/>
                    </template>
                    <template v-slot:[`item.ratings`]="{ value}" >
                        {{getRating(value)}}
                    </template>
                    <template v-slot:[`item.lastModifiedAt`]="{ value}" >
                        {{value.split("T")[0]}}
                    </template>
                </v-data-table>
            </v-col>
        </v-row>
    </v-container>

  </div>
</template>




<script>
import AddRec from '@/views/AdicionarRecurso.vue'
import NewPub from '@/views/NovaPublicação.vue'
import axios from 'axios'

export default {
    name: 'recursos',
    data() {
        return { 
            selected: [],
            filtro: '',
            resources: [],
            headers: [
                { text: 'Título', align: 'center', value: 'title'},
                { text: 'Tipo', align: 'center',value: 'resourceType' },
               // { text: 'Autor',align: 'center', value: 'autor' },
                { text: 'Classificação',align: 'center', value: 'ratings' },
                { text: 'Nº de downloads',align: 'center', value: 'nDownloads' },
                { text: 'Data de modificação',align: 'center', value: 'lastModifiedAt' },
                { text: '',align: 'center', sortable: false, value: 'idResource' }
            ],
            items: [
                {titulo: 'Este é o titulo 1', tipo: 'type 1', autor: 'Joao', classificacao: '9.8', ndownloads: '4', lastModificacao: '2018-2-13', id: 'a'},
                {titulo: 'Este é o titulo 2', tipo: 'type 2', autor: 'Ricardo', classificacao: '16.8', ndownloads: '6', lastModificacao: '2021-2-24', id: 'b'},
                {titulo: 'Este é o titulo 3', tipo: 'type 1', autor: 'Abel', classificacao: '3.2', ndownloads: '0', lastModificacao: '2016-11-17', id: 'c'},
                {titulo: 'Este é o titulo 4', tipo: 'type 0', autor: 'Joaquim', classificacao: '14.4', ndownloads: '1', lastModificacao: '2012-3-3', id: 'e'}
            ]
        }
    },
    components: {
        AddRec,
        NewPub
    },
    methods: {
        handleClick(value) {
          this.$router.push('/recurso/' + value.id)      
        },
        search() {
            console.log(this.filtro)
        },
        novoRecurso() {
            console.log("vou adicionar")
        },
        download() {
            console.log(JSON.stringify(this.selected))
        },
        novaPublicacao(value) {
            console.log(value)
        },
        getRating(lista){
            var rating = 0
            lista.forEach(elem => rating += elem.rating)
            console.log(rating / lista.length)
            return rating / lista.length
        }
    },
    created() {
        axios({
            method: "get",
            url: "http://localhost:8081/api/resource/",
        })
        .then(data => {
            this.resources = data.data;
        })
        .catch(err => {
            console.log(err)
        })
    }
}

</script>




<style>

#mytable table thead {
  background: #80dfff;
}


</style>