<template>
  <div id="perfil"  v-if="user">
    
    <v-container style="max-width: 85%" class="perfil">
        <v-card class="pa-6 user" color="grey lighten-5" outlined> 
                <v-row>
                  <v-col cols="1" sm="3">
                      <v-avatar size="150">
                          <v-img v-if="user.picture" src="https://digimedia.web.ua.pt/wp-content/uploads/2017/05/default-user-image.png"></v-img>
                          <v-img v-else src="https://digimedia.web.ua.pt/wp-content/uploads/2017/05/default-user-image.png"></v-img>
                      </v-avatar>
                  </v-col>

                  <v-col cols="1" sm="4" align="start">
                      <span style="font-size: 30px; color: #53a6bf;"> {{user.name}} <br/> </span>
                      <span style="font-size: 22px;"> <b>Estatuto: </b> {{user.role.type}} <br/> </span>
                      <span style="font-size: 22px;" > <b>Filiação: </b> {{user.role.affiliation}} <br/> </span>
                      <span style="font-size: 22px;"> <i> Registado desde {{user.registerDate.split("T")[0]}} </i></span>
                  </v-col>
                
                  <v-col align="right">
                    <v-btn @click="editarPerfil()" color='#53a6bf'> Editar Perfil </v-btn>
                    
                    <v-btn @click="mudarFoto()" color='#53a6bf'> Mudar Foto </v-btn>
                  </v-col>
                  
                </v-row>
               
            
        </v-card> 
        <v-card
            class="ma-2"
            max-width="700"
        >
        </v-card>
        <v-col v-for="n in list" :key="n.data">
            <v-card light flat >
              <v-container >
                <v-layout align-center>
                  <strong class="display-1 font-weight-regular mr-4">{{n.data.split("-")[2]}}</strong>
                  <v-layout column justify-end>
                    <div class="title font-weight-light">{{dataToDia(n.data)}}</div>
                    <div class="text-uppercase font-weight-light">{{dataToMes(n.data)}}</div>
                  </v-layout>
                 </v-layout>
               </v-container>
            </v-card>
            <v-card-text class="py-0">
              <v-col v-for="x in n.lista" :key="x.hora" cols="12" sm="5">
                <v-timeline align-top dense>
                  <v-timeline-item color="teal lighten-2" small>
                        <v-layout pt-3>
                          <v-flex xs3>
                            <strong style="font-size: 20px;">{{x.hora}}</strong>
                          </v-flex>
                          <v-flex>
                            <span style="font-size: 20px;">{{x.descri}}</span>
                            <div class="caption" >Blah blah</div>
                          </v-flex>
                        </v-layout>
                    </v-timeline-item>
                </v-timeline>

              </v-col>

                  
            </v-card-text>
        </v-col>    
        
    </v-container>
    
  

    

    
  </div>
</template>




<script>
import axios from 'axios'

export default {
    name: 'perfil',
    data() {
        return { 
            list:[],
            user: ''
         
        }
    },
    components: {
    },
    created() {
        axios({
            method: "get",
            url: "http://localhost:8081/api/user/1",
            headers: { "Content-Type": "multipart/form-data" },
        })
        .then(data => {
            this.user = data.data;
            this.list = 
        [{
        "data":"2021-02-10",
        "lista" : [
            {
            "hora":"16:10",
            "descri":"Carreguei um novo Recurso: x"
            }
            ]
        },
        {
        "data":"2021-02-11",
        "lista" : [
            {
            "hora":"16:11",
            "descri":"Carreguei um novo Recurso: x"
            },
            {
            "hora":"18:16",
            "descri":"Criei uma nova Publicação: y"
            }
            ]
        },
        {
        "data":"2021-02-12",
        "lista" : [
            {
            "hora":"16:15",
            "descri":"Carreguei um novo Recurso: x"
            },
            {
            "hora":"18:17",
            "descri":"Criei uma nova Publicação: y"
            }
            ]
        }]
            
            console.log(this.list)

            console.log(this.user)
        })
        .catch(err => {
            console.log(err)
        })
    },
    methods: {
      dataToDia(data) {
         console.log(data)
       
        let date = new Date(data.split("-")[0],data.split("-")[1], data.split("-")[2]); // 2020-06-21
        let day = date.toLocaleString('pt-pt', { weekday: 'long' });

        return day.charAt(0).toUpperCase() + day.substring(1) 
      },
      dataToMes(data) {
         console.log(data)
       
        let date = new Date(data.split("-")[0],data.split("-")[1], data.split("-")[2]); // 2020-06-21
        let Month = date.toLocaleString('pt-pt', { month: 'long' });
        return Month.charAt(0).toUpperCase() + Month.substring(1) +" "+data.split("-")[0]
      }
    
    
    }
}

</script>




<style>

.user {
    text-align: left;
    border-radius: 5px;
    margin: 10px;
}

.perfil {
    background-color: #a9a9a94a;
    border-radius: 5px;
    margin-top: 20px;
}

</style>