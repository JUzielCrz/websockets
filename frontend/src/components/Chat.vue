<template>
  <div class="chat-container">
    <h1>🧠 Chat WebSocket</h1>
    <input v-model="message" @keyup.enter="sendMessage" placeholder="Escribe tu mensaje..." />
    <ul>
      <li v-for="(msg, index) in messages" :key="index">{{ msg }}</li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

const message = ref('')
const messages = ref([])
let stompClient = null

onMounted(() => {
  const socket = new SockJS('/ws')
  stompClient = Stomp.over(socket)
  stompClient.connect({}, () => {
    stompClient.subscribe('/topic/public', (msg) => {
      const payload = JSON.parse(msg.body)
      messages.value.push(`${payload.sender}: ${payload.content}`)
    })
  })
})

const sendMessage = () => {
  if (message.value.trim() !== '') {
    stompClient.send('/app/chat.send', {}, JSON.stringify({
      sender: 'Jeft',
      content: message.value
    }))
    message.value = ''
  }
}
</script>

<style scoped>
.chat-container {
  max-width: 500px;
  margin: auto;
  padding: 1rem;
  font-family: sans-serif;
}
input {
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 1rem;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin-bottom: 0.5rem;
  background: #eee;
  padding: 0.5rem;
  border-radius: 4px;
}
</style>
