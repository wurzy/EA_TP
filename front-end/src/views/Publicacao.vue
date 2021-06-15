<template>
    <div id="publicacao" class="publicacao" >

        <v-container v-if="pub" style="padding: 40px 80px 0px 80px;">

            <v-row >

                <v-col  sm="2" style="justify-items: center; display: flex; flex-direction: column; justify-content: center;">
                    <v-img :src="`http://localhost:8081/api/user/image/thumbnail/` + pub.user.picture"></v-img>
                </v-col>
                <v-col  sm="8" style="padding-left: 10px; padding-top: 20px;" >
                    <v-text-field v-if="editing" type="text" no-resize v-model="newTitle" rows="4"></v-text-field>
                    <span v-else style="font-size: 25px; color: #53a6bf;"> <b>{{ pub.title }}</b> <br/> </span>
                    <span> 
                        <b>Recurso: </b> 
                        <span style="cursor:pointer;" @click="handleClickResource(pub.resource.id)">{{ pub.resource.title }} <br/> </span>
                    </span>
                    <span> {{ pub.user.name }} há {{ pub.createdAt.split("T")[0] }} </span>
                </v-col>
                
                <v-col sm="2" align="right" v-if="idUser==pub.user.idUser">
                    <v-icon style="font-size:18px;margin-right:10px" @click="editing=!editing"> mdi-pencil </v-icon>
                    <v-icon color="red" @click="removePost()"> mdi-close </v-icon>
                </v-col>
            </v-row>
            <v-row class="corpo">
                <v-textarea v-if="editing" type="text" no-resize v-model="newBody" rows="4"></v-textarea>
                <span v-else> {{ pub.body }} </span>
            </v-row>
            <v-row>
                <v-col v-if="editing" align="right">
                    <v-btn small @click="editPost()">Guardar</v-btn>
                </v-col>
            </v-row>
        <br/>
        <hr style="border-top: 2px solid #b1b1b1;">
        </v-container>

        <v-container style="padding: 40px 80px;">

            <v-textarea solo v-model="com" rows="4" counter maxlength="400" label="Insira aqui o seu comentário..."></v-textarea>
            <v-btn style="float: right;" @click="addComment">Comentar</v-btn> 

            <br/>

            <v-row no-gutters style="width: 95%;">
              <v-col v-for="n in pub.comments" :key="n.idComment" cols="12" sm="12">
                  <v-row style="padding-top: 60px;">
                    <v-col  sm="2" style="display:inline-flex">
                        <v-img :src="`http://localhost:8081/api/user/image/thumbnail/` + n.idUser.picture"></v-img>
                    </v-col>

                    <v-col  sm="9" style="border-radius: 5px; background-color: white;">
                        <v-row class="pa-0"> 
                            <v-col sm="11">
                                <span style="font-size: 20px; color: #ec6200;"> {{ n.idUser.name}} </span>
                            </v-col>
                            <v-col sm="1" align="right">
                                <v-icon v-if="idUser==n.idUser.idUser" small color="red" @click="removeComentario()"> mdi-close </v-icon>
                            </v-col>
                            <br/> 
                        </v-row>
                        <span> {{ n.createdAt | moment("from") }} <br/> </span>
                        <hr>
                        <span style="padding-top: 10px;"> {{ n.body }} </span>
                    </v-col>
                    
                    

                  </v-row>
              </v-col>
            </v-row>

        </v-container>

    </div>
</template>



<script>
import axios from 'axios'

export default {
    name: 'Publicacao',
    data() {
        return { 
            editing: false,
            idUser: null,
            com:"",
            token: localStorage.getItem('jwt'),
            pub: '',
            newBody: '',
            newTitle: ''
        }
    },
    methods: {
        handleClickResource(id) {
            this.$router.push('/recursos/'+id)
        },
        sorted(lista) {
            return lista.sort((a,b) => (a.createdAt < b.createdAt) ? 1 : ((b.createdAt < a.createdAt) ? -1 : 0))
        },
        addComment() {
            var json = {};
            json['idUser'] = 1
            json['body'] = this.com
            json['createdAt'] = new Date().toISOString()
            axios({
                method: "post",
                url: "http://localhost:8081/api/post/comment/" + this.$route.params.id,
                data: json,
                headers: { "Authorization" : this.token},
            })
            .then(() => {
                    alert('Comentário efetuado com sucesso!')
                    this.$router.go()
                })
            .catch(err => {
                    console.log(err)
                    alert('Não foi possível adicionar novo comentário')
                })
        },
        removePost(){
            var id = this.$route.params.id
            axios({
                method: "post",
                url: "http://localhost:8081/api/post/delete/"+id,
                headers: { "Authorization" : this.token }
            })
            .then(() => {
                this.$router.push('/')
            })
            .catch(err => {
                console.log(err)
            })
        },
        editPost(){
            var id = this.$route.params.id
            var json = {}
            json['title'] = this.newTitle
            json['body'] = this.newBody
            axios({
                method: "post",
                url: "http://localhost:8081/api/post/update/"+id,
                headers: { "Authorization" : this.token },
                data: json
            })
            .then(() => {
                this.$router.go()
            })
            .catch(err => {
                console.log(err)
            })
        },
        removeComentario(){
            var id = this.$route.params.id
            axios({
                method: "post",
                url: "http://localhost:8081/api/post/comment/delete/"+id,
                headers: { "Authorization" : this.token }
            })
            .then(() => {
                this.$router.go()
            })
            .catch(err => {
                console.log(err)
            })
        }
    },
    created() {
        axios({
            method: "get",
            url: "http://localhost:8081/api/post/"+this.$route.params.id,
        })
        .then(data => {
            this.pub = data.data;
            this.newBody = this.pub.body
            this.newTitle = this.pub.title
            this.pub.comments = this.sorted(this.pub.comments)
            axios({
                method: "post",
                url: "http://localhost:8081/api/user/token/",
                data: this.token,
            })
            .then(data => {
                this.idUser = data.data.idUser
            })
            .catch(err => {
                console.log(err)
            })
        })
        .catch(err => {
            console.log(err)
        })
    }
}

</script>




<style>

.publicacao {
    border-radius: 5px;
    background-color: #c1bfbf61;
    width: 60%;

    margin: auto;
}

.corpo {
    padding: 10px;
    border-radius: 5px;
    background-color: white;
    margin: 20px;
}

.img_perfil {
    margin-left: 20px;
    text-align: right;
    max-height: 100px;
    max-width: 100px;
    height: auto;
    width: 100%;
    padding-right: 10px;
}

</style>