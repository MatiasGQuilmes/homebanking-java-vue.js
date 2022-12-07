const app = Vue.createApp({

    data(){
        return {
            nombreUsuario: "",
            apellidoUsuario: "",
            clients: [],
            accounts: [],
            urlApi: "http://localhost:8080/api/clients/current",
            account: [],

            loans: [],
            
            cards: [],

            id: new URLSearchParams(location.search).get("id"),

            creditDebit: "",
            silverGoldTitanium: ""

        }
    },
    created(){
      
        this.loadData(this.urlApi);   
    },   
    methods:{
       loadData(URL){
        axios.get(URL) //hace una peticion de tipo get a una url, si existe te devuelve una promesa y si no existe te da un error 404 o 405
            .then(res => {
                this.clients = res.data
                console.log(this.clients);
                this.accounts = this.clients.accounts.sort(this.order)
                console.log(this.accounts);
             
                this.nombreUsuario = this.clients.firstName
                this.apellidoUsuario = this.clients.lastName
                
                this.loans = this.clients.loans.sort(this.order)
                
                console.log(this.loans);

                this.cards = this.clients.cards.sort(this.order)
                console.log(this.cards);

                this.account = this.accounts.find(e => e.id == this.id)

            })
         
        },
        logout(){
            
            axios.post('/api/logout').then(()=>this.loadData(this.urlApi))

        },
        createAccount(){
            axios.post("/api/clients/current/accounts").then(()=>this.loadData(this.urlApi))
        },
        balanceFormateado(numero){
            return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'ARS' }).format(numero)
        },
        order(a,b){
            return a.id - b.id 
        },
        createCard(){
            axios.post(`/api/clients/current/cards?type=${this.creditDebit}&color=${this.silverGoldTitanium}`).then(res => console.log(res))

        }      
    
    }


    
}).mount('#app')


