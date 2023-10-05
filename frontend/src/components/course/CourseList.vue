<template>
  <h1>List Courses</h1>
</template>

<script setup>
import courseService from "../../services/course.service";
const courses = ref([]);

const getAll = async () => {
  try {
    const response = await courseService.getAll();
    return (courses.value = response.data);
  } catch (error) {
    return console.error(error);
  }
};
</script>
