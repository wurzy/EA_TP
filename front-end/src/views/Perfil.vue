<template>
  <div id="perfil"  v-if="user">
    
    <v-container style="max-width: 85%" class="perfil">
        <v-card class="pa-6 user" color="grey lighten-5" outlined> 
                <v-row>
                  <v-col cols="1" sm="3">
                    
                      <input style="display: none" type="file" @change="onFileSelected" ref="fileInput">
                      <v-avatar @click="$refs.fileInput.click()" size="150">
                          <v-img v-if="this.imageOri" :src="this.image" @mouseover="image=imageHover" @mouseleave="image=imageOri"></v-img>
                          <v-img v-else :src="this.image" @mouseover="imageAux=imageHover" @mouseleave="image=imageDefault"></v-img>
                      </v-avatar>
                  
                    <v-btn  small v-if="this.imageTemp" @click="mudarFoto" color='#53a6bf'> Guardar </v-btn>
                  </v-col>

                  <v-col cols="1" sm="4" align="start">
                      <span style="font-size: 30px; color: #53a6bf;"> {{user.name}} <br/> </span>
                      <span style="font-size: 22px;"> <b>Estatuto: </b> {{user.role.type}} <br/> </span>
                      <span style="font-size: 22px;" > <b>Filiação: </b> {{user.role.affiliation}} <br/> </span>
                      <span style="font-size: 22px;"> <i> Registado desde {{user.registerDate.split("T")[0]}} </i></span>
                  </v-col>
                  
                  <v-col v-if="this.visivel" align="right">
                    <EditPerfil :user="this.user"/>
                    
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
                  <strong class="display-1 font-weight-regular mr-4">{{n.date.split("-")[2]}}</strong>
                  <v-layout column justify-end>
                    <div class="title font-weight-light">{{dataToDia(n.date)}}</div>
                    <div class="text-uppercase font-weight-light">{{dataToMes(n.date)}}</div>
                  </v-layout>
                 </v-layout>
               </v-container>
            </v-card>
            <v-card-text class="py-0">
              <v-col v-for="x in n.timeline" :key="x.hora" cols="12" sm="5">
                <v-timeline align-top dense>
                  <v-timeline-item color="teal lighten-2" small>
                        <v-layout pt-3>
                          <v-flex xs3>
                            <strong style="font-size: 20px;">{{x.time.slice(0,5)}}</strong>
                          </v-flex>
                          <v-flex>
                            <span style="font-size: 20px;">{{x.state}}</span>
                            
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
import EditPerfil from "@/views/EditarPerfil.vue"

export default {
    name: 'perfil',
    data() {
        return { 
            list:[],
            user: '',
            hover: false,
            image: null,
            imageTemp: null,
            imageOri: null,
            imageDefault: "https://digimedia.web.ua.pt/wp-content/uploads/2017/05/default-user-image.png",
            imageHover: "https://i.ibb.co/rf1BNkr/unknown.png",
            visivel: false

         
        }
    },
    components: {
      EditPerfil
    },
    created() {
        if(this.$route.params.id==1){this.visivel=true}
        else{this.visivel=false}
        axios({
            method: "get",
            url: "http://localhost:8081/api/user/"+this.$route.params.id+"/",
            headers: { "Content-Type": "multipart/form-data" },
        })
        .then(data => {
            this.user = data.data;
            console.log(this.user)
            axios({
            method: "get",
            url: "http://localhost:8081/api/user/image/thumbnail/"+this.user.picture+"/",
            responseType: 'blob',})
            .then(res => {
              this.imageOri = URL.createObjectURL(res.data)
              this.image =  URL.createObjectURL(res.data)
            })
            .catch(err => {
              console.log(err)
            })
        })
        .catch(err => {
            console.log(err)
        })

        axios({
        method: "get",
        url: "http://localhost:8081/api/user/timeline/"+this.$route.params.id+"/",})
        .then(res => {
          this.list = res.data
        })
        .catch(err => {
          console.log(err)
        })
        
        
    },
    methods: {
      dataToDia(data) {
       
        let date = new Date(data.split("-")[0],data.split("-")[1], data.split("-")[2]); // 2020-06-21
        let day = date.toLocaleString('pt-pt', { weekday: 'long' });

        return day.charAt(0).toUpperCase() + day.substring(1) 
      },
      dataToMes(data) {
       
        let date = new Date(data.split("-")[0],data.split("-")[1], data.split("-")[2]); // 2020-06-21
        let Month = date.toLocaleString('pt-pt', { month: 'long' });
        return Month.charAt(0).toUpperCase() + Month.substring(1) +" "+data.split("-")[0]
      },
      onFileSelected(event){
        this.imageTemp = event.target.files[0]
      },
      mudarFoto(){
        const fd =  new FormData();
        fd.append('image', this.imageTemp, this.imageTemp.name)
        axios({
                method: "post",
                url: "http://localhost:8081/api/user/image/1/",
                data: fd,
                headers: { "Content-Type": "multipart/form-data" , "Authorization" : "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjM2MzE0MTUsInN1YiI6IkVBIiwiaWRVc2VyIjoxLCJuYW1lIjoiVsOhbHRlciBDYXJ2YWxobyIsImVtYWlsIjoiMUB1bWluaG8ucHQiLCJwYXNzd29yZCI6IjEiLCJsZXZlbCI6InByb2R1dG9yIiwicmVnaXN0ZXJEYXRlIjoxNjEyOTU2MDUzMDAwLCJkZXNjcmlwdGlvbiI6Ik91dHJhIERlc2MgIDExcmnDp8OjbyIsInBpY3R1cmUiOiIxLmpwZyIsImJsb2NrZWQiOmZhbHNlLCJyb2xlIjp7ImlkUm9sZSI6MTMsInR5cGUiOiJPbDExYSIsImFmZmlsaWF0aW9uIjoiT2wxMWUifSwiaXNzIjoiR3J1cG8gMDMifQ.hTywAawtTllFUOpQMedHIXuigU95c4kSXSc8_JK3iL8"},
            })
            .then( res => {
                  this.imageOri = URL.createObjectURL(this.imageTemp)
                  this.image = URL.createObjectURL(this.imageTemp)
                  alert('Recurso adicionado com sucesso!',res)
                  

                })
            .catch(err => {
                    console.log(err)
                    alert('Não foi possível adicionar novo recurso')
                   
                })
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
.active {
  background: grey;
}
.perfil {
    background-color: #a9a9a94a;
    border-radius: 5px;
    margin-top: 20px;
}

</style>