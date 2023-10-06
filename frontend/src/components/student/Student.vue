<template>
  <div class="bg-white shadow-md p-4 rounded-md">
    <h2 class="text-2xl font-semibold text-center text-indigo-600 mb-4">
      Student Details
    </h2>
    <div class="grid grid-cols-2 gap-4">
      <div class="text-gray-700">
        <p>
          <strong class="text-indigo-600">First Name:</strong>
          {{ student.firstName }}
        </p>
        <p>
          <strong class="text-indigo-600">Last Name:</strong>
          {{ student.lastName }}
        </p>
        <p>
          <strong class="text-indigo-600">Email:</strong> {{ student.email }}
        </p>
      </div>
      <div class="text-gray-700">
        <h3 class="text-lg font-semibold mb-2 text-indigo-600">Courses:</h3>
        <ul class="list-disc pl-4">
          <li v-for="(course, index) in courses" :key="index">{{ course }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, watch } from "vue";
import studentService from "../../services/student.service";
import { useRoute } from "vue-router";
import { useToast } from "vue-toast-notification";

const toast = useToast();
const route = useRoute();

const student = ref({
  lastname: "",
  firstname: "",
  email: "",
});

onMounted(() => {
  getStudent(route.params.id);
});

watch(
  () => route.params.id,
  async (newId) => {
    student.value = await getStudent(newId);
  }
);

const getStudent = async (id) => {
  try {
    const response = await studentService.get(id);
    return (student.value = response);
  } catch (error) {
    toast.error(error.message, {
      position: "top-right",
    });
    return console.error(error);
  }
};
</script>
