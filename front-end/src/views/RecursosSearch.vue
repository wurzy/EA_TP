<template>
    <div id="recursos-search" class="recursos-search" >
        <v-container style="padding: 40px 10% 0px">
            <h1 class="title">Tipo de Recursos</h1>
            <br/>
            <v-col align="center">
                <a style="color:black;" href="http://localhost:8080/recursos/type/all">Ver Todos</a>
            </v-col>
            <!--
            <v-col v-for="n in ordered" :key="n.idRecurso" cols="12" sm="12">
                <span class="letter" v-if="n.length == 1"> {{ n.toUpperCase() }} <hr> </span>
                <li style="cursor: pointer;" v-else @click="handleClick(n)"> {{ n }} </li>
            </v-col>-->
            <v-col v-for="n in types" :key="n" cols="12" sm="12">
                <li style="cursor: pointer;" @click="handleClick(n)"> {{ n }} </li>
            </v-col>
        </v-container>
    </div>
</template>


<script>
import axios from 'axios'

export default {
    name: 'RecursosSearch',
    data() {
        return { 
            //abc: 'a',
            //ordered: [],
            types: []
        }
    },
    created() {
        axios({
            method: "get",
            url: "http://localhost:8081/api/resource/types/",
        })
        .then(data => {
            this.types = data.data.types
            this.loading=false
        })
        .catch(err => {
            console.log(err)
        })
        /*
        var abc = ""
        var ord = this.recs1.sort((a,b) => (a.tipo > b.tipo) ? 1 : ((b.tipo > a.tipo) ? -1 : 0))
        for(var i = 0; i < ord.length; i++) {
            if (i == 0) {
                abc = ord[i].tipo.charAt(0).toLowerCase()
                this.ordered.push(abc,ord[i].tipo+" ")
            } else {
                if(ord[i].tipo.charAt(0).toLowerCase() == this.abc) {
                    this.ordered.push(ord[i].tipo+" ")
                }
                else {
                    abc = ord[i].tipo.charAt(0).toLowerCase()
                    this.ordered.push(abc,ord[i].tipo+" ")
                }
            }
        }
        console.log(this.ordered)  */  
    },
    methods: {
        handleClick(value) {
            this.$router.push('/recursos/type/' + value)      
        }
    }
}

</script>


<style>

#recursos-search .letter{
    font-size: 40px;
    margin: 0px !important;
}

#recursos-search .title {
    font-size: 30px !important;
    text-decoration: underline;
    text-align: center;
    margin-top: 10px;
    text-underline-offset: 7px;
    text-decoration-thickness: 6px;
    text-decoration-color: #e88b00;
}



</style>