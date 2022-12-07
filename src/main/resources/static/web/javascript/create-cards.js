const app = Vue.createApp({

    data(){
        return {
            nombre: "",
            apellido:"",
            creditDebit: "",
            silverGoldTitanium: ""

        }
    },
    created(){
        this.data('/api/clients/current')
    },
    methods:{
        data(url){
            axios.get(url)
            .then(res => {
                this.nombre = res.data.firstName
                this.apellido = res.data.lastName;
            })
        },
        createCard(){
            if(this.creditDebit == ""){
                console.log("falta el tipo de tarjeta");
            }else if(this.silverGoldTitanium == ""){
                console.log("falta el color de tarjeta");
            }else{
                axios.post(`/api/clients/current/cards?type=${this.creditDebit}&color=${this.silverGoldTitanium}`).then(()=> window.location.assign('./cards.html'))
            }
        
        }      
    
    }


    
}).mount('#app')
