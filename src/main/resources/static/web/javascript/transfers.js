const app = Vue.createApp({
    data(){
        return{
            nombre:"",
            apellido: "",

            clients:[],

            amount: "",
            description: "",
            accountOrigin: "",
            accountDestiny:"",
        
            accounts: [],
            nroCuenta: "",
         
            urlApi: "/api/clients/current",

            option: "",
          
        }
    },
    created(){
        this.data(this.urlApi)
    },
    methods:{   
        data(url){
            axios.get(url)
            .then(res =>{
                this.nombre = res.data.firstName;
                this.apellido = res.data.lastName;
                
                this.clients = res.data
             
                this.accounts = this.clients.accounts
               
                
                this.numbers = this.accountOriginList.number
            

                
/*                 this.accountOrigin1 = this.accountOriginList.find(account => account.number == this.accountOrigin)
                this.id = this.accountOrigin1.id */
                
            })
        },
        createTransfer(){

                if(this.amount === 0 || this.description === "" || this.accountOrigin === ""|| this.accountDestiny === "" ){
                    Swal.fire({
                        icon: 'warning',
                        title: 'Todos los campos son obligatorios'
                    })
                }else{
                    axios.post(`/api/transactions?amount=${this.amount}&description=${this.description}&accountOrigin=${this.accountOrigin}&accountDestiny=${this.accountDestiny}`)
                    .then(()=>{  Swal.fire(
                        'congratulation',
                        'success',
                        ),setTimeout(()=>{
                            window.location.pathname='/web/accounts.html'
                        },"2000")})
                    .catch(()=>{
                        Swal.fire({
                            icon: 'warning',
                            title: 'the account destiny not exist'
                        })
                    })
             
                }

               
        },
        alerta(){
            Swal.fire({
                title: 'Are you sure you want to transfer?',
                text: "Check that the data are correct",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, transfer!'
              }).then((result) => {
                if (result.isConfirmed) {

                    this.createTransfer()

                   
                }
              })
        }
       
    },computed:{
        destinyAccount(){
            this.destinationAccounts = this.accounts.filter(account => account.number != this.accountOrigin)
            console.log(this.destinationAccounts);
        }
    }


}).mount('#app')