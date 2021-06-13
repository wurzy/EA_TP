<template>
    <div id="publicacao" class="publicacao" >

        <v-container v-if="pub" style="padding: 40px 80px 0px 80px;">

            <v-row >
                <v-col cols="12" sm="2" style="justify-items: center; display: flex; flex-direction: column; justify-content: center;">
                    <v-img :src="`http://localhost:8081/api/user/image/thumbnail/` + pub.user.picture"></v-img>
                </v-col>
                <v-col cols="12" sm="10" style="padding-left: 10px; padding-top: 20px;" >
                    <span style="font-size: 25px; color: #53a6bf;"> <b>{{ pub.title }}</b> <br/> </span>
                    <span> 
                        <b>Recurso: </b> 
                        <span style="cursor:pointer;" @click="handleClickResource(pub.resource.id)">{{ pub.resource.title }} <br/> </span>
                    </span>
                    <span> {{ pub.user.name }} há {{ pub.createdAt.split("T")[0] }} </span>
                </v-col>
            </v-row>
            <v-row class="corpo">
                <span> {{ pub.body }} </span>
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
                    <v-col cols="12" sm="2" style="display:inline-flex">
                        <v-img :src="`http://localhost:8081/api/user/image/thumbnail/` + n.idUser.picture"></v-img>
                    </v-col>
                    <v-col cols="12" sm="10" style="border-radius: 5px; background-color: white;">
                        <span style="font-size: 20px; color: #ec6200;"> {{ n.idUser.name}} <br/> </span>
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
            com:"",
            pub: ''
        }
    },
    methods: {
        handleClickResource(id) {
            this.$router.push('/recursos/'+id)
        },
        addComment() {
            var json = {};
            json['idUser'] = 1
            json['body'] = this.com
            json['createdAt'] = new Date().toISOString()
            axios({
                method: "post",
                url: "http://localhost:8081/api/post/comment/" + this.$route.params.id,
                data: json
            })
            .then(() => {
                    alert('Comentário efetuado com sucesso!')
                    this.$router.go()
                })
            .catch(err => {
                    console.log(err)
                    alert('Não foi possível adicionar novo comentário')
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