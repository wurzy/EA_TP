<template>
    <div id="addRecurso">
        <v-dialog max-width="900px" v-model="show">
            <template v-slot:activator="{ on }">
               <v-btn v-on="on" color='#80dfff'> Novo recurso </v-btn>
            </template>
            <v-card >
                <v-card-text >
                   
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
                        
                        <v-col id="titulo">
                            <h1 >Adicionar Recurso</h1>
                        </v-col>

                        <v-col class="pa-2">
                            <v-text-field 
                            hide-details
                            dense
                            type="text" 
                            v-model="titulo" 
                            label="Título"
                            outlined
                            ></v-text-field>
                        </v-col>

                        <v-col class="pa-2">
                            <v-text-field 
                            hide-details
                            dense
                            type="text" 
                            v-model="descricao" 
                            label="Descrição"
                            outlined
                            ></v-text-field>
                        </v-col>

                        <v-col class="pa-2">
                            <v-text-field 
                            hide-details
                            dense
                            type="text" 
                            v-model="tipo" 
                            label="Tipo"
                            outlined
                            ></v-text-field>
                        </v-col>

                        <v-col class="pa-2">
                            <v-text-field 
                            hide-details
                            dense
                            type="text" 
                            v-model="data" 
                            label="Data de criação"
                            outlined
                            ></v-text-field>
                        </v-col>

                        <div style="align:center; display:flex">
                            <span style="padding: 18px 0 0 10px"> Visibilidade: </span>
                            <v-switch
                             style="margin-left:10px"
                             v-model="visibilidade"
                            ></v-switch>
                        </div>

                        <v-row >
                            <v-col cols=2>
                                <span style="padding: 18px 0 0 10px"> Ficheiros: </span>
                            </v-col>
                            <v-col cols="10"> 
                              <v-data-table
                                hide-default-footer
                                :headers="headers"
                                :items="files"
                                item-key="name"
                                class="elevation-1">
                                <template v-slot:[`item.lastModified`]="{ value }" >
                                    <v-btn
                                      @click="remove(value)"
                                      color="red"
                                      outlined
                                      small
                                      elevation="8"
                                      icon
                                    >
                                    X 
                                    </v-btn>
                                </template>
                                <template v-slot:[`item.size`]="{ value }" >
                                    {{(value/1024).toFixed(1)}}KB
                                </template>
                                <template v-slot:[`item.url`]="{ value }" >
                                   <!-- <span @mouseover="hover = true; url = value" @mouseleave="hover = false" > -->
                                    <span @click="hover = true; findImage(value)">
                                        <v-icon>mdi-eye</v-icon>
                                    </span>
                                </template>
                                </v-data-table>
                            </v-col>
                        </v-row>
          
                        <v-col align="right">
                            <v-btn
                                style="margin-right:24px"
                                @click="chooseFiles()"
                                color="green"
                                outlined
                                small
                                elevation="8"
                                icon
                                >
                                <v-icon small>
                                    mdi-plus
                                </v-icon> 
                            </v-btn>
                            <input
                              ref="uploader"
                              class="d-none"
                              type="file"
                              multiple
                              accept="/*"
                              @change="onFileChanged"
                            >
                        </v-col>


                        <v-col align="right">
                          <v-btn v-ripple="{ class: 'primary--text' }" width="150" style="height:40px" class="white--text" elevation="1" v-on:click="submeter()" color="#00ace6">Submeter</v-btn>
                          <v-btn v-ripple="{ class: 'primary--text' }" width="150" style="margin-left:10px;height:40px" class="white--text" elevation="1" v-on:click="cancelar()" color="#527a7a">Cancelar</v-btn>
                        </v-col>

                    </v-container>
                </v-card-text>
            </v-card>
        </v-dialog> 
    </div>
</template>


<script>
import axios from 'axios'

export default {
    name: "addRecurso",
    data() {
        return{
          ficheiroAtual: null,
          url:'',
          hover: false,
          show:false,
          visibilidade:true,
          titulo:'',
          descricao:'',
          data:null,
          tipo:'',
          files:[],
          headers: [
                { text: 'Nome', align: 'center',sortable: false, value: 'name'},
                { text: 'Tamanho', align: 'center',sortable: false, value: 'size' },
                { text: 'Visualizar', align: 'center',sortable: false, value: 'url' },
                { text: '', align: 'center', sortable: false, value: 'lastModified'}
            ],
        }
    },
    methods: {
        findImage(url) {
            this.ficheiroAtual = null
            this.files.forEach(f => {
                if (f.url==url) {
                    this.ficheiroAtual = f;
                }
            })
        },
        cancelar() {
            this.show=false;
            this.visibilidade=true,
            this.titulo='',
            this.descricao='',
            this.data=null,
            this.tipo='',
            this.files=[]
        },
        submeter() {
            var bodyFormData = new FormData();
            bodyFormData.append('idUser', 1);
            bodyFormData.append('idResource', 0)
            bodyFormData.append('title',this.titulo)
            bodyFormData.append('body',this.descricao)
            bodyFormData.append('createdAt',new Date().toISOString().slice(0, 19).replace('T', ' '))
            axios({
                method: "post",
                url: "http://localhost:8081/api/resource/",
                data: bodyFormData,
                headers: { "Content-Type": "multipart/form-data" },
            })
            .then(data => {
                    alert('Recurso adicionado com sucesso!')
                    this.cancelar();
                    this.$router.push('/recursos/' + data.data.idResource)
                })
            .catch(err => {
                    console.log(err)
                    alert('Não foi possível adicionar novo recurso')
                    this.cancelar();
                })
        },
        remove(value) {
            var index = this.files.map(function(item) { return item.lastModified; }).indexOf(value);
            this.files.splice(index, 1);
        },
        chooseFiles() {
          this.$refs.uploader.click()
        },
        onFileChanged(e) { 
          let file = e.target.files;
          file.forEach( f => {
            var newObject  = {
               'lastModified'     : f.lastModified,
               'lastModifiedDate' : f.lastModifiedDate,
               'name'             : f.name,
               'size'             : f.size,
               'type'             : f.type,
               'url'              : URL.createObjectURL(f) 
            }; 
            this.files.push(newObject)
          })
        }
    }
}

</script>

<style> 
.switch {
 margin-top: 0px;
 padding-top: 0px;
}

#titulo h1{
    color: #00ace6;
}



embed{
  height: 500px;
  width: 500px;    
}



</style>