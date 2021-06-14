<template>
    <div id="newRate">
        <v-dialog max-width="900px" v-model="show">
            <template v-slot:activator="{ on }">
                <v-btn small v-on="on">Classificar</v-btn>
            </template>
            <v-card >
                <v-card-text >
                    <v-container>
                        
                        <v-col id="titulo">
                            <h1>Nova classificação</h1>
                        </v-col>

                        <v-col style="margin-top:30px">
                            <v-slider
                              v-model="rate"
                              color="#00ace6"
                              min="1"
                              max="100"
                              thumb-label
                            ></v-slider>
                        </v-col>

                        <v-col align="right">
                          <v-btn :loading="loading" v-ripple="{ class: 'primary--text' }" width="150" style="height:40px" class="white--text" elevation="1" v-on:click="submeter()" color="#00ace6">Submeter</v-btn>
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
    name: "newRate",
    data() {
        return{
          show:false,
          loading:false,
          rate:0
        }
    },
    props: {
        value: Number
    },
    methods: {
        cancelar() {
            this.show=false
            this.loading=false
        },
        submeter() {
            console.log(this.value)
            console.log(this.rate)
            var json={}
            json['rating'] = this.rate
            json['idUser'] = 1
            axios({
                method: "post",
                url: "http://localhost:8081/api/resource/rate/"+this.value,
                data: json,
                headers: { "Authorization" : "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjM2MzE0MTUsInN1YiI6IkVBIiwiaWRVc2VyIjoxLCJuYW1lIjoiVsOhbHRlciBDYXJ2YWxobyIsImVtYWlsIjoiMUB1bWluaG8ucHQiLCJwYXNzd29yZCI6IjEiLCJsZXZlbCI6InByb2R1dG9yIiwicmVnaXN0ZXJEYXRlIjoxNjEyOTU2MDUzMDAwLCJkZXNjcmlwdGlvbiI6Ik91dHJhIERlc2MgIDExcmnDp8OjbyIsInBpY3R1cmUiOiIxLmpwZyIsImJsb2NrZWQiOmZhbHNlLCJyb2xlIjp7ImlkUm9sZSI6MTMsInR5cGUiOiJPbDExYSIsImFmZmlsaWF0aW9uIjoiT2wxMWUifSwiaXNzIjoiR3J1cG8gMDMifQ.hTywAawtTllFUOpQMedHIXuigU95c4kSXSc8_JK3iL8"},
            })
            .then((data) => {
                    console.log(data.data)
                    alert('Classificação efetuada com sucesso!')
                    this.cancelar()
                    this.$router.go()
                })
            .catch(err => {
                    console.log(err)
                    alert('Não foi possível classificar o recurso')
                    this.cancelar();
                })
        }
    }
}

</script>

<style> 

</style>