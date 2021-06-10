<template>
    <div id="recurso" class="recurso" >

        <v-dialog
          v-model="hover"
          max-width= '500px'
          max-height= '500px'
          >
          <v-card v-if="this.ficheiroAtual">
            <v-img v-if="this.ficheiroAtual.type.split('/')[0]=='image'" :src="this.ficheiroAtual.url"></v-img>
            <embed v-else-if="this.ficheiroAtual.type=='application/pdf'" :src="this.ficheiroAtual.url"/>
            <v-card-title v-else>Não é possível visualizar o ficheiro! {{this.ficheiroAtual.type}}</v-card-title>
            </v-card>
        </v-dialog>

        <v-container>
            <h1> {{item.titulo}}</h1>

            <v-row style="padding: 70px 0 0 0">
                <v-col cols=2 offset=2 class="pa-0" @click="updateSelected('info')">   
                  <v-card elevation="0" outlined :style="selected=='info' ? 'background-color: inherit' : 'background-color:#ddd'">
                      <v-card-title class="justify-center">Informação</v-card-title>
                  </v-card>
                </v-col>

                <v-col cols=2 class="ma-0 pa-0" @click="updateSelected('view')">
                  <v-card elevation="0" outlined :style="selected=='view' ? 'background-color: inherit' : 'background-color:#ddd'">
                      <v-card-title class="justify-center">Visualizar</v-card-title>
                  </v-card>   
                </v-col>
            </v-row>

            <v-row>
                <v-col cols=8 offset="2" class="pa-0">
                    <v-card v-if="this.selected=='info'" elevation="0" outlined style="background-color: inherit;text-align: left;">
                        <v-row >
                            <v-col cols=6 class="pa-10">
                                <b>Tipo: </b> {{this.item.tipo}}<br>
                                <br>
                                <b>Descrição: </b> {{this.item.descricao}}<br>
                                <br>
                                <b>Data de Criação: </b> {{this.item.dataCriacao}}<br>
                                <br>
                                <b>Data de Registo: </b> {{this.item.dataRegisto}}<br>
                                <br>
                                <b>Classificação: </b> {{this.item.classificacao}}<br>
                            </v-col>
                            <v-col cols=6 class="pa-10">
                                <b>Autor: </b> {{this.item.autor}}<br>
                                <br>
                                <b>Ultima Modificação: </b> {{this.item.lastModificacao}}<br>
                                <br>
                                <b>Número de publicações: </b> {{this.item.npubs}}<br>
                                <br>
                                <b>Número de downloads: </b> {{this.item.ndownloads}}<br>
                                <br>
                                <b>Tamanho total: </b> {{getTotalSize()}} KB<br>
                            </v-col>
                        </v-row>
                    </v-card>
                    <v-card v-else elevation="0" outlined style="background-color: inherit">
                        <v-row class="pa-6">
                            <v-col >
                                <v-data-table
                                    height= '190px'
                                    hide-default-footer
                                    :headers="headers"
                                    :items="item.files"
                                    item-key="name"
                                    class="elevation-1">
                                    <template v-slot:[`item.size`]="{ value }" >
                                            {{(value/1024).toFixed(1)}}KB
                                        </template>
                                        <template v-slot:[`item.url`]="{ value }" >
                                            <span @click="hover = true; findImage(value)">
                                                <v-icon>mdi-eye</v-icon>
                                            </span>
                                        </template>

                                </v-data-table>
                            </v-col>
                        </v-row>

                        <v-row class="pa-0 ma-0">
                            <v-col align="left" class="pa-8">
                                <v-btn v-ripple="{ class: 'primary--text' }" width="150" style="height:40px" class="white--text" elevation="1" v-on:click="download()" color="#00ace6">Download</v-btn>
                                <v-btn v-ripple="{ class: 'primary--text' }" width="150" style="margin-left:10px;height:40px" class="white--text" elevation="1" v-on:click="publicar()" color="#527a7a">Publicar</v-btn>
                            </v-col>
                        </v-row>
                    </v-card>   
                </v-col>
            </v-row>

        </v-container>

    </div>
</template>



<script>

export default {
    name: 'Recurso',
    data() {
        return { 
            ficheiroAtual: null,
            hover: false,
            selected: "info",
            item: {
                titulo: 'Este é o titulo 1', 
                tipo: 'type 1', 
                autor: 'Joao', 
                classificacao: '9.8', 
                ndownloads: '4', 
                npubs: '1',
                lastModificacao: '2018-2-13', 
                id: 'a',
                dataCriacao: '2015-2-1',
                dataRegisto: '2015-1-26',
                descricao: 'esta é a sua desrição, vamos fazer parecer maior para ver se cabe no sitio dela',
                files: [
                    { "lastModified": 1620228345351, "lastModifiedDate": "2021-05-05T15:25:45.351Z", "name": "brasil2.png", "size": 57139, "type": "image/png", "url": "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Flag_of_Brazil.svg/1200px-Flag_of_Brazil.svg.png" },
                    { "lastModified": 1605831821740, "lastModifiedDate": "2020-11-20T00:23:41.740Z", "name": "Ficha_exercícios_1.pdf", "size": 605385, "type": "application/pdf", "url": "http://www.africau.edu/images/default/sample.pdf" }
                ]
            },     
            headers: [
                { text: 'Nome', sortable: false, value: 'name'},
                { text: 'Tamanho', align: 'center',sortable: false, value: 'size' },
                { text: 'Visualizar', align: 'center',sortable: false, value: 'url' }
            ],  
        }
    },
    methods: {
        updateSelected(value) {
            this.selected = value
        },
        getTotalSize() {
            var total = 0
            this.item.files.forEach( f => {
                total += f.size
            })
            return (total/1024).toFixed(1)
        },
        findImage(url) {
            this.ficheiroAtual = null
            this.item.files.forEach(f => {
                if (f.url==url) {
                    this.ficheiroAtual = f;
                }
            })
        },
        download() {
            this.item.files.forEach(f => {
                console.log(f)
            })
        },
        publicar() {
            var id = this.$route.params.id
            alert('publicação no id: ' + id)
        }
    }
}

</script>




<style>

.recurso {
    text-align: center;
    height: 100vh;
}

.recurso h1{
    margin-top:30px;
    color: #00ace6
}





</style>