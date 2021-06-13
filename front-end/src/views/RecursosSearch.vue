<template>
    <div id="recursos-search" class="recursos-search" >
        <v-container style="padding: 40px 10% 0px">
            <h1 class="title">Tipo de Recursos</h1>
            <br/>
            <v-col v-for="n in ordered" :key="n.idRecurso" cols="12" sm="12">
                <span class="letter" v-if="n.length == 1"> {{ n.toUpperCase() }} <hr> </span>
                <li style="cursor: pointer;" v-else @click="handleClick(n)"> {{ n }} </li>
            </v-col>
        </v-container>
    </div>
</template>


<script>
export default {
    name: 'RecursosSearch',
    data() {
        return { 
            abc: 'a',
            ordered: [],
            recs1: [
                {imagemURL: false, titulo: 'Nome do Recurso 1', estado: 'Novo', tipo: 'ATipo1', autor: 'João',  dataPublicacao: '2012-3-3', idRecurso: 1},
                {imagemURL: false, titulo: 'Nome do Recurso 2', estado: 'Atualizado', tipo: 'ATipo2', autor: 'Ricardo', dataPublicacao: '2012-3-3', idRecurso: 2},
                {imagemURL: false, titulo: 'Nome do Recurso 3', estado: 'Indisponível', tipo: 'CTipo3', autor: 'Abel', dataPublicacao: '2012-3-3', idRecurso: 3},
                {imagemURL: false, titulo: 'Nome do Recurso 4', estado: 'Atualizado', tipo: 'BTipo1', autor: 'Joaquim', dataPublicacao: '2012-3-3', idRecurso: 4},
                {imagemURL: false, titulo: 'Nome do Recurso 4', estado: 'Atualizado', tipo: 'XTipo1', autor: 'Joaquim', dataPublicacao: '2012-3-3', idRecurso: 4}
            ], 
        }
    },
    created() {
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
        console.log(this.ordered)    
    },
    methods: {
        handleClick(value) {
            this.$router.push('/recursos/' + value)      
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