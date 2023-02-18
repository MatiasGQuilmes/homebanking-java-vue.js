const app = Vue.createApp({

    data(){
        return {
        
            firstName:"",
            lastName:"",
            email: "",
            password:""
          
         
        }
    },  
    methods:{
        login(){
            axios.post('/api/login',`email=${this.email}&password=${this.password}`).then(()=> window.location.href = "/web/accounts.html")
        },
        register(){

            if(!this.firstName || !this.lastName || !this.email || !this.password ||  !this.email|| !this.password){
                Swal.fire({
                    icon: 'warning',
                    title: 'todos los campos son obligatorios'
                })
            }
            axios.post('/api/clients',`firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`)
            .then(()=> this.login())
          
        }


    }
   
}).mount('#app')

