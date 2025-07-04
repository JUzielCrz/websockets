<!-- src/components/ProgresoView.vue -->
<template>
  <div>
    <h2>Progreso del proceso</h2>
    <button @click="iniciarProceso">Iniciar Proceso</button>
    <ul>
      <li v-for="(msg, index) in mensajes" :key="index">{{ msg }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      socket: null,
      mensajes: []
    }
  },
  mounted() {
    this.conectarWebSocket()
  },
  methods: {
    conectarWebSocket() {
      this.socket = new WebSocket("ws://localhost:8080/ws/progreso")
      this.socket.onmessage = (event) => {
        this.mensajes.push(event.data)
      }
    },
    iniciarProceso() {
      fetch("http://localhost:8080/api/proceso/iniciar", {
        method: "POST"
      }).then(res => {
        if (res.ok) {
          console.log("Proceso iniciado")
        }
      })
    }
  }
}
</script>
