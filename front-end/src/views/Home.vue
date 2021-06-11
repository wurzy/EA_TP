<template>
    <div id="home" class="home">

     <v-container>
      
      <v-row no-gutters>
        <v-col cols="12" sm="6" md="8" class="publicacoes">
          <h1>Publicações</h1>
          <v-container class="pubs">
            <v-row no-gutters>
              <v-col v-for="n in list" :key="n" cols="12" sm="6">
                <v-card class="pa-6 pub" outlined  @click="handleClick(n.idPub)">
                  <v-row>
                    <v-col cols="12" sm="4" style="display:inline-flex">
                        <img v-if="n.imagemURL" class="img" :src="require('../' + n.imagemURL.substr(3))"/>
                        <img v-else class="img" src="../assets/default.jpg"/>
                    </v-col>
                    <v-col cols="12" sm="8">
                        <span style="font-size: 20px; color: #53a6bf;"> {{ n.titulo }} <br/> </span>
                        <span> <b>Recurso: </b>{{ n.recurso }} <br/> </span>
                        <span> {{ n.autor }} há {{ n.dataPublicacao }} </span>
                    </v-col>
                  </v-row>
                </v-card> 
              </v-col>
            </v-row>
          </v-container>
          <v-btn style="margin-top: 20px;" v-if="all" @click="handleLess">Ver menos</v-btn> 
          <v-btn style="margin-top: 20px;" v-else @click="handleMore"> Ver mais</v-btn> 
        </v-col>

        <v-col cols="6" md="4" class="recursos">
            <h1>Novos Recursos</h1>
            <v-container class="recs">
            <v-row no-gutters>
              <v-col v-for="n in recs" :key="n" cols="12" sm="12">
                <v-card class="pa-6 rec" @click="handleClick2(n.idRec)">
                  <v-row>
                    <v-col cols="12" sm="4" style="display:inline-flex">
                        <img v-if="n.imagemURL" class="img" :src="require('../' + n.imagemURL.substr(3))"/>
                        <img v-else class="img" src="https://digimedia.web.ua.pt/wp-content/uploads/2017/05/default-user-image.png"/>
                    </v-col>
                    <v-col cols="12" sm="8">
                        <span style="font-size: 20px; text-decoration: underline"> {{ n.titulo }} <br/> </span>
                        <span> <b>Estado: </b>{{ n.estado }} <br/> </span>
                        <span> <b>Tipo: </b>{{ n.tipo }} <br/> </span>
                        <span> {{ n.autor }} há {{ n.dataPublicacao }} </span>
                    </v-col>
                  </v-row>
                </v-card>
              </v-col>
            </v-row>
          </v-container>
        </v-col>
    
      </v-row>
    </v-container>
    
    </div>

</template>



<script>

export default {
    name: 'recursos',
    data() {
        return { 
            all: false,
            limite: 8,
            list: [],
            pubs: [
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 1', recurso: 'recurso 1', autor: 'João',  dataPublicacao: '2012-3-3', idPub: 1},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 2', recurso: 'recurso 2', autor: 'Ricardo', dataPublicacao: '2012-3-3', idPub: 2},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 3', recurso: 'recurso 1', autor: 'Abel', dataPublicacao: '2012-3-3', idPub: 3},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 4', recurso: 'recurso 0', autor: 'Joaquim', dataPublicacao: '2012-3-3', idPub: 4},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 5', recurso: 'recurso 3', autor: 'Filipa', dataPublicacao: '2012-3-3', idPub: 5},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 6', recurso: 'recurso 4', autor: 'Hugo', dataPublicacao: '2012-3-3', idPub: 6},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 7', recurso: 'recurso 1', autor: 'Válter', dataPublicacao: '2012-3-3', idPub: 7},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 8', recurso: 'recurso 0', autor: 'Joaquim', dataPublicacao: '2012-3-3', idPub: 8},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 9', recurso: 'recurso 3', autor: 'Filipa', dataPublicacao: '2012-3-3', idPub: 9},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 10', recurso: 'recurso 4', autor: 'Hugo', dataPublicacao: '2012-3-3', idPub: 10},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 11', recurso: 'recurso 1', autor: 'Válter', dataPublicacao: '2012-3-3', idPub: 11},
                {imagemURL: "../assets/default.jpg", titulo: 'Este é o titulo 12', recurso: 'recurso 8', autor: 'José', dataPublicacao: '2012-3-3', idPub: 12}
            ], 
            recs: [
                {imagemURL: false, titulo: 'Nome do Recurso 1', estado: 'Novo', tipo: 'Novo Tipo', autor: 'João',  dataPublicacao: '2012-3-3', idRecurso: 1},
                {imagemURL: false, titulo: 'Nome do Recurso 2', estado: 'Atualizado', tipo: 'Tipo 1', autor: 'Ricardo', dataPublicacao: '2012-3-3', idRecurso: 2},
                {imagemURL: false, titulo: 'Nome do Recurso 3', estado: 'Indisponível', tipo: 'Novo Tipo', autor: 'Abel', dataPublicacao: '2012-3-3', idRecurso: 3},
                {imagemURL: false, titulo: 'Nome do Recurso 4', estado: 'Atualizado', tipo: 'Tipo 2', autor: 'Joaquim', dataPublicacao: '2012-3-3', idRecurso: 4}
            ], 
        }
    },
    components: {
    },
    created() {
        /*axios({
            method: "get",
            url: "http://localhost:8081/api/user/",
            headers: { "Content-Type": "multipart/form-data" },
        })
        .then(data => {
            this.users = data.data;*/
            this.list = this.pubs.slice(0,this.limite);
        /*})
        .catch(err => {
            console.log(err)
        })*/
    },
    methods: {
        handleMore(){
            this.list = this.pubs
            this.all = true
        },
        handleLess(){
            this.list = this.pubs.slice(0,this.limite)
            this.all = false
        },
        handleClick(value) {
          this.$router.push('/publicacoes/' + value)      
        },
        handleClick2(value) {
          this.$router.push('/recursos/' + value)      
        }
    }
}

</script>




<style>

.home {
    text-align: center;
    height: 100vh;
}

.home h1{
    font-size: 30px;
    text-decoration: underline;
    text-align: left;
    margin-top: 10px;
    text-underline-offset: 7px;
    text-decoration-thickness: 6px;
    text-decoration-color: #e88b00;
}

.publicacoes {
    padding-right: 30px !important;
}

.pubs {
    background-color: #a9a9a94a;
    border-radius: 5px;
    margin-top: 30px;
}

.pub {
    text-align: left;
    border-radius: 5px;
    margin: 10px;
}

.recursos {
    max-height: 840px;
    border-left: 2px solid #969090;
    background-color: white;
    padding-left: 30px !important;
    display: flex;
    flex-direction: column;
    align-items: center;
    right: 0;
    position: fixed;
}

.recursos h1 {
    text-align: center !important;
    text-decoration: none !important;
    background-color: #e88b00bd;
    width: fit-content;
    padding: 0px 5px;
}

.recs {
    margin-top: 20px;
}

.rec {
    text-align: left;
    border-radius: 5px;
    margin: 10px;
    margin-bottom: 30px;
}

.img {
    margin: auto;
    text-align: right;
    max-height: 80px;
    max-width: 80px;
}

</style>