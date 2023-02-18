const app = Vue.createApp({

    data(){
        return {
        
         
            email: "",
            password:""
          
         
        }
    },  
    methods:{
   
        login(){

            if(this.email == ""|| this.password == ""){
                Swal.fire({
                    icon: 'warning',
                    title: 'Todos los campos son obligatorios'
                })
            }else{
                axios.post('/api/login',`email=${this.email}&password=${this.password}`)
                .then(()=> window.location.href = "/web/accounts.html")  
                .catch(() => Swal.fire({
                    icon: 'warning',
                    title: 'Credenciales Invalidas'
                }))
            }

        

            
        }


    }
   
}).mount('#app')

