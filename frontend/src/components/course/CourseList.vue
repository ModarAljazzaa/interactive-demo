<template>
  <button
    @click="openCreateCourseModal"
    class="rounded-lg bg-green-700 px-4 py-2 text-sm font-medium text-white hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 dark:hover:bg-green-700 dark:focus:ring-green-800 mb-3"
  >
    Create Course
  </button>
  <table class="w-full text-center text-sm text-gray-500 dark:text-gray-400">
    <thead
      class="bg-gray-50 text-xs uppercase text-gray-700 dark:bg-gray-700 dark:text-gray-400"
    >
      <tr>
        <th v-for="column in columns" :key="column.key" class="px-4 py-4">
          {{ column.label }}
        </th>
        <th class="px-4 py-4">action</th>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="course in courses"
        :key="course.id"
        class="border-b dark:border-gray-700"
      >
        <td class="px-4 py-3">
          {{ course.title }}
        </td>
        <td class="px-4 py-3">
          {{ course.description }}
        </td>

        <td class="flex items-center px-4 py-3">
          <router-link
            :to="'/courses/edit/' + course.id"
            class="flex items-center justify-center rounded-lg bg-blue-700 px-4 py-2 text-sm font-medium text-white hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-green-300 mr-2"
          >
            Edit
          </router-link>
          <button
            @click="showConfirmDeleteModal(course)"
            class="flex items-center justify-center rounded-lg bg-red-700 px-4 py-2 text-sm font-medium text-white hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-green-300 mr-2"
          >
            Delete
          </button>
          <router-link
            :to="'/courses/' + course.id"
            class="flex items-center justify-center rounded-lg bg-green-700 px-4 py-2 text-sm font-medium text-white hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300"
            >Show</router-link
          >
        </td>
      </tr>
    </tbody>
  </table>
  <confirm-delete
    v-show="isConfirmDeleteModalVisible"
    modalHeadline="Delete course?"
    deleteMessage="Are you sure?"
    @deleteRecordEvent="deleteCourse"
    @close="closeConfirmDeleteModal"
  ></confirm-delete>
  <create-course
    v-show="isCreateCourseVisible"
    @close="closeCreateCourseModal"
  ></create-course>
</template>
<script setup>
import CreateCourse from "./CreateCourse.vue";
import courseService from "../../services/course.service";
import ConfirmDelete from "../ConfirmDeleteModel.vue";
import { ref, onMounted } from "vue";
import { useToast } from "vue-toast-notification";

const toast = useToast();
const courses = ref([]);
const currentUser = ref(null);
const isConfirmDeleteModalVisible = ref(false);
const isCreateCourseVisible = ref(false);
const getAll = async () => {
  try {
    const response = await courseService.getAll();
    console.log(response);
    return (courses.value = response);
  } catch (error) {
    toast.error(error.message, {
      position: "top-right",
    });
    return console.error(error);
  }
};

onMounted(() => {
  getAll();
});

const emit = defineEmits(["edit", "delete"]);

const editCourse = (id) => {
  emit("edit", id);
};

const deleteCourse = async () => {
  try {
    await courseService.delete(currentUser.value.id);
    closeConfirmDeleteModal();
    getAll();
  } catch (error) {
    toast.error(error.message, { position: "top-right" });
    return console.error(error);
  }
};

const showConfirmDeleteModal = (course) => {
  currentUser.value = course;
  isConfirmDeleteModalVisible.value = true;
};

const closeConfirmDeleteModal = () => {
  isConfirmDeleteModalVisible.value = false;
  getAll();
};

const openCreateCourseModal = () => {
  isCreateCourseVisible.value = true;
};

const closeCreateCourseModal = () => {
  isCreateCourseVisible.value = false;
  getAll();
};

const columns = [
  { key: 1, label: "title" },
  { key: 2, label: "description" },
];
</script>
