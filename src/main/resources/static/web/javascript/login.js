const app = Vue.createApp({

    data(){
        return {
        
         
            email: "",
            password:""
          
         
        }
    },  
    methods:{
   
        login(){
        
            axios.post('/api/login',`email=${this.email}&password=${this.password}`).then(()=> window.location.href = "/web/accounts.html")
         
        }


    }
   
}).mount('#app')

