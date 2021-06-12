<template>
    <div id="publicacao" class="publicacao" >

        <v-container style="padding: 40px 80px 0px 80px;">

            <v-row>
                <v-col cols="12" sm="2" style="justify-items: center; display: flex; flex-direction: column; justify-content: center;">
                    <img v-if="pub.imagemURL" class="img_perfil" :src="require('../' + pub.imagemURL.substr(3))"/>
                    <img v-else class="img_perfil" src="https://digimedia.web.ua.pt/wp-content/uploads/2017/05/default-user-image.png"/>
                </v-col>
                <v-col cols="12" sm="10" style="padding-left: 10px; padding-top: 20px;">
                    <span style="font-size: 25px; color: #53a6bf;"> <b>{{ pub.titulo }}</b> <br/> </span>
                    <span> <b>Recurso: </b>{{ pub.recurso }} <br/> </span>
                    <span> {{ pub.autor }} há {{ pub.dataPublicacao }} </span>
                </v-col>
            </v-row>
            <v-row class="corpo">
                <span> {{ pub.conteudo }} </span>
            </v-row>
        <br/>
        <hr style="border-top: 2px solid #b1b1b1;">
        </v-container>

        <v-container style="padding: 40px 80px;">

            <v-textarea solo v-model="com" rows="4" counter maxlength="400" label="Insira aqui o seu comentário..."></v-textarea>
            <v-btn style="float: right;" @click="addComment">Comentar</v-btn> 

            <br/>

            <v-row no-gutters style="width: 95%;">
              <v-col v-for="n in coms" :key="n" cols="12" sm="12">
                  <v-row style="padding-top: 60px;">
                    <v-col cols="12" sm="2" style="display:inline-flex">
                        <img v-if="n.imagemURL" class="img" style="border-radius: 50px;" :src="require('../' + n.imagemURL.substr(3))"/>
                        <img v-else class="img" style="border-radius: 50px;" src="https://digimedia.web.ua.pt/wp-content/uploads/2017/05/default-user-image.png"/>
                    </v-col>
                    <v-col cols="12" sm="10" style="border-radius: 5px; background-color: white;">
                        <span style="font-size: 20px; color: #ec6200;"> {{ n.autor}} <br/> </span>
                        <span> há {{ n.dataComentario }} <br/> </span>
                        <hr>
                        <span style="padding-top: 10px;"> {{ n.conteudo }} </span>
                    </v-col>
                  </v-row>
              </v-col>
            </v-row>

        </v-container>

    </div>
</template>



<script>

export default {
    name: 'Publicacao',
    data() {
        return { 
            com:"",
            pub: {
                imagemURL: false, 
                titulo: 'Título da Publicação', 
                recurso: 'Recurso', 
                autor: 'João', 
                conteudo: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
                dataPublicacao: '2012-3-3', 
                idPub: 1
            },
            coms: [
                {imagemURL: false, autor: 'João',  dataComentario: '2012-3-3', conteudo: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempo!', idComentario: 1},
                {imagemURL: false, autor: 'Pedro', dataComentario: '2012-3-4', conteudo: 'Lorem ipsum dolor sit amet...', idComentario: 2},
                {imagemURL: false, autor: 'José', dataComentario: '2012-3-4', conteudo: 'Lorem ipsum dolor sit amet... Tempo.', idComentario: 3}
            ]
        }
    },
    methods: {
        addComment() {  
            console.log(this.com)
        },
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