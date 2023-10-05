<template>
  <div class="bg-white">
    <header class="bg-white w-full shadow-md mb-3">
      <nav class="container mx-auto px-6 py-3">
        <div class="flex justify-between items-center">
          <router-link
            to="/"
            class="text-2xl font-bold text-gray-800 hover:text-blue-600"
            >Interactive Demo</router-link
          >

          <div class="flex space-x-4">
            <router-link to="/courses" class="text-gray-800 hover:text-blue-600"
              >Courses</router-link
            >
            <router-link
              to="/students"
              class="text-gray-800 hover:text-blue-600"
              >Students</router-link
            >
            <router-link
              to="/create-student"
              class="text-gray-800 hover:text-blue-600"
              >Create Student</router-link
            >
          </div>
        </div>
      </nav>
    </header>
  </div>
</template>

<script>
export default {
  name: "Header",
};
</script>
