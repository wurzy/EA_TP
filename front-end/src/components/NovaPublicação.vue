<template>
    <div id="newPub">
        <v-dialog max-width="900px" v-model="show">
            <template v-slot:activator="{ on }">
                <v-btn small v-on="on">+ Publicação</v-btn>
            </template>
            <v-card >
                <v-card-text >
                    <v-container>
                        
                        <v-col id="titulo">
                            <h1 >Criar Publicação</h1>
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
                            <v-textarea
                            hide-details
                            type="text" 
                            v-model="descricao" 
                            label="Descrição"
                            outlined
                            ></v-textarea>
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

export default {
    name: "newPub",
    data() {
        return{
          show:false,
          titulo:'',
          descricao:'',
        }
    },
    props: {
        value: String
    },
    methods: {
        cancelar() {
            this.show=false;
            this.titulo='',
            this.descricao=''
        },
        submeter() {
            alert('Submetido com sucesso no id: ' + this.value)
            this.cancelar();
        },
    }
}

</script>

<style> 

#titulo h1{
    color: #00ace6;
}

</style>