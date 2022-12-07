const app = Vue.createApp({
    data(){
        return{
            urlApi: "/api/loans",

            nombreUsuario: "",
            apellidoUsuario: "",

            accountDestiny: "",

            loanType: "",

            payments: [],

            request: {
                id:"",
                amount: "",
                payments:"",
                destinationAccount: "",
    
            },
           
            loans: [],

            apiClients:"/api/clients/current",

            accounts: []
        }
    },
    created(){
        this.data(this.urlApi);
        this.dataClients(this.apiClients);
    },
    methods:{  
        data(urlApi){
            axios.get(urlApi)
                .then(res => {
                    this.loans = res.data;
                   



                })
        },
        dataClients(url){
            axios.get(url)
                .then(res => {
                    this.accounts= res.data.accounts;
                    console.log(this.accounts);

                    this.nombreUsuario = res.data.firstName
                    this.apellidoUsuario = res.data.lastName


                })
        },
        balanceFormateado(numero){
            return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'ARS' }).format(numero)
        },
        alerta(){
            Swal.fire({
                title: 'Are you sure you want to transfer?',
                text: this.balanceFormateado(this.request.amount) + " " +  `and monthly installments are ${this.balanceFormateado(this.request.amount / this.request.payments)}`,
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, transfer!'
              }).then((result) => {
                if (result.isConfirmed) {
         
                    this.createLoan()       
                }
              })
        },
        paymentsSelected(){
            if(this.loanType != ""){
                let loanSelected = this.loans.find(loan => loan.name == this.loanType)
                this.request.id = loanSelected.id
                this.payments = loanSelected.payments
                return this.payments
            }
      
        },
        createLoan(){
            axios.post('/api/loans',{"id": this.request.id, "amount": this.request.amount, "payments":this.request.payments, "destinationAccount": this.request.destinationAccount})
            .then(()=> window.location.href = "/web/accounts.html")
            .catch(err => Swal.fire(
                err.response.data,
                ' ',
                'error'
              ))
        }
        
    },
    computed:{
        chargerId(){
            this.request.id= this.loanType
        }
    }

}).mount('#app');