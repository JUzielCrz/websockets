<template>
  <div class="container">
    <h2>Progreso del Proceso Largo</h2>

    <button @click="iniciarProceso">Iniciar proceso</button>

    <progress :value="porcentaje" max="100"></progress>
    <p>{{ porcentaje }}%</p>

    <ul>
      <li v-for="(msg, index) in mensajes" :key="index">{{ msg }}</li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'

const mensajes = ref([])
const porcentaje = ref(0)
let socket = null

onMounted(() => {
  socket = new WebSocket('ws://localhost:8080/ws/progreso')

  socket.onmessage = (event) => {
    const mensaje = event.data
    console.log('MENSAJE:', mensaje)
    mensajes.value.push(mensaje)

    // Si el mensaje contiene un porcentaje, extraerlo
    const match = mensaje.match(/Avance: (\d+)%/)
    if (match) {
      porcentaje.value = parseInt(match[1])
      console.log('AVANCE DETECTADO:', porcentaje.value)
    }

    // Si el proceso terminó, aseguramos 100%
    if (mensaje.includes('Proceso terminado')) {
      porcentaje.value = 100
    }
  }

  socket.onopen = () => console.log('WebSocket conectado')
  socket.onclose = () => console.log('WebSocket cerrado')
  socket.onerror = (e) => console.error('WebSocket error:', e)
})

onBeforeUnmount(() => {
  if (socket) socket.close()
})

function iniciarProceso() {
  porcentaje.value = 0
  mensajes.value = []

  fetch('http://localhost:8080/api/proceso/iniciar', {
    method: 'POST'
  })
}
</script>

<style scoped>
.container {
  padding: 2rem;
  font-family: sans-serif;
  max-width: 500px;
  margin: auto;
}
button {
  margin-bottom: 1rem;
}
progress {
  width: 100%;
  height: 30px;
  background-color: #eee;
  border: 1px solid #ccc;
  appearance: auto;
  transition: all 0.3s ease;
}

</style>
