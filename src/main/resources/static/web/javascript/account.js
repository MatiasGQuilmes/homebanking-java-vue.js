const app = Vue.createApp({

    data(){
        return {
            apiClients: "/api/clients/current",
            urlApi: "/api/accounts",
            accounts: [],
            clients: [],
            
            nombreUsuario: "",
            apellidoUsuario: "",
            
            account:[],
            id: new URLSearchParams(location.search).get("id"),
            
            transactions: []
        }
    },
    created(){   
        this.loadData(this.urlApi);
        this.loadClient(this.apiClients)
    },   
    methods:{
       loadData(URL){
            axios.get(URL) 
                .then(res => {
                    this.accounts = res.data
                    this.account = this.accounts.find(e => e.id == this.id)
           
                    this.transactions = this.account.transactions.sort(this.order)
            

            
            }).catch(err => {console.error(err);})     
        },
        loadClient(url){
            axios.get(url)
                .then(res => {
                    this.clients = res.data
                    this.nombreUsuario = this.clients.firstName
                    this.apellidoUsuario = this.clients.lastName
                })  
        },
        
        order(a,b){
            return a.id - b.id
        },
        balanceFormateado(numero){
            return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'ARS' }).format(numero)
        },






    }
   
}).mount('#app')

