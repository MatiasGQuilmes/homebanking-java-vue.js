const app = Vue.createApp({
    data() {
      return {
          info: null,
          clientes: [],
          api:"http://localhost:8080/clients",
          name: "",
          lastName:"",
          email: "",
          clientEdit: [],

      }
    },
    created(){
  
      this.loadData(this.api) 
  
  
    },
   
    methods:{
      loadData(url){
          axios.get(url)
            .then(res => {
                this.info = res
                this.clientes = res.data._embedded.clients
              
          }  
          
      ).catch(err => console.log(err.message))
  
      },
      addClient(){

        if(!this.name.length && !this.email.length && !this.lastName.length ){
            alert("DEBE INGRESAR DATOS")
        }else{
          let cliente = {
            firstName: this.name,
            lastName: this.lastName,
            email: this.email
          }
       
          this.postCliente(cliente)
       
        }
        
          
      },
      postCliente(cliente){//postClient: obtiene los datos del nuevo cliente usando AJAX al back-end o servicio REST.
          axios.post(this.api, cliente).then(()=>{
            this.loadData(this.api)
          }) //Realiza una petición HTTP de tipo POST a la URL /cients con la librería axios.
      },
      deleteClient(cliente){
          axios.delete(cliente._links.self.href).then(()=>this.loadData(this.api))//si no le pones arrow function, el primer click va a tomar lo q esta antes del then y despues lo q esta despues
      },
      editClient(cliente){
          this.name = cliente.firstName
          this.lastName = cliente.lastName
          this.email = cliente.email

          this.clientEdit = cliente

      },
      acceptChange(){
          axios.put(this.clientEdit._links.client.href, {firstName: this.name, lastName: this.lastName, email: this.email}).then(()=>this.loadData(this.api))
      }


    }
  }).mount('#app')
  
  
  
  