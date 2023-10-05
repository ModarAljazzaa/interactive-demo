<template>
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
        v-for="student in students"
        :key="student.id"
        class="border-b dark:border-gray-700"
      >
        <td class="px-4 py-3">
          {{ student.firstName }}
        </td>
        <td class="px-4 py-3">
          {{ student.lastName }}
        </td>
        <td class="px-4 py-3">
          {{ student.email }}
        </td>

        <td class="flex items-center px-4 py-3">
          <button
            @click="editStudent(student.id)"
            class="flex items-center justify-center rounded-lg bg-blue-700 px-4 py-2 text-sm font-medium text-white hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-green-300 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800 mr-2"
          >
            Edit
          </button>
          <button
            @click="showConfirmDeleteModal(student)"
            class="flex items-center justify-center rounded-lg bg-red-700 px-4 py-2 text-sm font-medium text-white hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-green-300 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800 mr-2"
          >
            Delete
          </button>
          <button
            @click="showStudent(student.id)"
            class="flex items-center justify-center rounded-lg bg-green-700 px-4 py-2 text-sm font-medium text-white hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
          >
            Show
          </button>
        </td>
      </tr>
    </tbody>
  </table>
  <confirm-delete
    v-show="isConfirmDeleteModalVisible"
    modalHeadline="Delete student?"
    deleteMessage="Are you sure?"
    @deleteRecordEvent="deleteStudent"
    @close="closeConfirmDeleteModal"
  ></confirm-delete>
</template>
<script setup>
import studentService from "../../services/student.service";
import ConfirmDelete from "../ConfirmDeleteModel.vue";
import { ref, onMounted } from "vue";

const students = ref([]);
const currentUser = ref(null);
const isConfirmDeleteModalVisible = ref(false);
const getAll = async () => {
  try {
    const response = await studentService.getAll();
    return (students.value = response.data);
  } catch (error) {
    return console.error(error);
  }
};

const showConfirmDeleteModal = (student) => {
  currentUser.value = student;
  isConfirmDeleteModalVisible.value = true;
  console.log("Student => ", student);
};
onMounted(() => {
  getAll();
});

const emit = defineEmits(["edit", "delete"]);

const editStudent = (id) => {
  emit("edit", id);
};

const deleteStudent = async () => {
  try {
    await studentService.delete(currentUser.value.id);
    closeConfirmDeleteModal();
    getAll();
  } catch (error) {
    return console.error(error);
  }
};

const closeConfirmDeleteModal = () => {
  isConfirmDeleteModalVisible.value = false;
};

const columns = [
  { key: 1, label: "first Name" },
  { key: 2, label: "last Name" },
  { key: 3, label: "email" },
];
</script>
