const app = Vue.createApp({

    data(){
        return {
        
         
            email: "",
            password:""
          
         
        }
    },  
    methods:{
   
        login(){
             if(this.email == ""){
                Swal.fire({
                    icon: 'warning',
                    title: 'The email is required'
                })
            }else if(!this.password){
                Swal.fire({
                    icon: 'warning',
                    title: 'The password is required'
                })
            }else{
                axios.post('/api/login',`email=${this.email}&password=${this.password}`)
                .then(()=> window.location.href = "/web/accounts.html")  
                .catch(() => Swal.fire({
                    icon: 'warning',
                    title: 'invalid credentials'
                }))
            }

        

            
        }


    }
   
}).mount('#app')

