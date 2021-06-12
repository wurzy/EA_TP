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
                :items-per-page="5"
                class="elevation-1 click"
                :value="null"
                @click:row="handleClick"
                item-key="title">
                    <template v-slot:[`item.idResource`]="{ value }" >
                        <NewPub :value="value"/>
                    </template>
                    <template v-slot:[`item.ratings`]="{ value }" >
                        <div class="stars" :data-percent="getRating(value)">
                            ★★★★★
                        </div>
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
            resourcesInitial: [],
            headers: [
                { text: 'Título', align: 'center', value: 'title'},
                { text: 'Tipo', align: 'center',value: 'resourceType' },
                { text: 'Autor',align: 'center', value: 'idUser.name' },
                { text: 'Classificação', sortable: false, align: 'center', value: 'ratings' },
                { text: 'Nº de downloads',align: 'center', value: 'nDownloads' },
                { text: 'Data de modificação',align: 'center', value: 'lastModifiedAt' },
                { text: '',align: 'center', sortable: false, value: 'idResource' }
            ],
        }
    },
    components: {
        AddRec,
        NewPub
    },
    methods: {
        handleClick(value) {
          this.$router.push('/recursos/' + value.idResource)      
        },
        download() {
            this.selected.forEach(elem => {
                this.incrementaDownload(elem.idResource)
            })
        },
        incrementaDownload(id){
            axios({
            method: "post",
            url: "http://localhost:8081/api/resource/inc_downloads/" + id,
            })
            .then(data => {
                console.log(data.data)
            })
            .catch(err => {
                console.log(err)
            })
        },
        getRating(lista){
            var rating = 0
            lista.forEach(elem => rating += elem.rating)
            rating = rating / lista.length
            if (lista.length==0) return 0
            else return rating
        },
        filtrar(obj) {
            var name = obj.title
            var re = new RegExp(this.filtro, 'i');
            return name.match(re)
        },
        search() {
            if (this.filtro!=""){
                this.resources = this.resourcesInitial.filter(this.filtrar)
            }
            else {
                this.resources = this.resourcesInitial 
            }
         }
    },
    created() {
        axios({
            method: "get",
            url: "http://localhost:8081/api/resource/",
        })
        .then(data => {
            this.resources = data.data;
            this.resourcesInitial = data.data;
        })
        .catch(err => {
            console.log(err)
        })
    }
}

</script>




<style>

@import '../assets/rating.css';

#mytable table thead {
  background: #80dfff;
}

.click {
	position:relative;
}

.click[value="null"]:BEFORE {
	display:none;
}

</style>