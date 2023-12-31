<template>
  <div class="fixed inset-0 flex items-center justify-center z-50">
    <div class="modal-container">
      <div class="bg-white w-full max-w-md p-4 rounded-lg shadow-lg">
        <div class="text-3xl font-semibold mb-6">Create Student</div>
        <form @submit.prevent="onSubmit">
          <div class="mb-4">
            <label for="firstName" class="block text-gray-600"
              >First Name:</label
            >
            <input
              v-bind="firstName"
              type="text"
              id="lastname"
              class="w-full border rounded px-3 py-2"
            />
            <span class="text-red-600 mt-3">
              {{ errors["student.firstName"] }}</span
            >
          </div>
          <div class="mb-4">
            <label for="lastName" class="block text-gray-600">Last Name:</label>
            <input
              v-bind="lastName"
              type="text"
              id="firstname"
              class="w-full border rounded px-3 py-2"
            />
            <span class="text-red-500 mt-3">
              {{ errors["student.lastName"] }}</span
            >
          </div>
          <div class="mb-4">
            <label for="email" class="block text-gray-600">Email:</label>
            <input
              v-bind="email"
              type="email"
              id="email"
              class="w-full border rounded px-3 py-2"
            />
            <span class="text-red-500 mt-3">
              {{ errors["student.email"] }}</span
            >
          </div>
          <div class="flex justify-between mt-4">
            <button
              type="submit"
              class="rounded-lg bg-green-700 px-4 py-2 text-sm font-medium text-white hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
            >
              Create Student
            </button>
            <button
              type="button"
              @click="close"
              class="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"
            >
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import studentService from "../../services/student.service";
import { reactive, ref } from "vue";
import * as yup from "yup";
import { useField, useForm } from "vee-validate";
import { useToast } from "vue-toast-notification";

// const student = ref({ lastname: "", firstname: "", email: "" });
const toast = useToast();
const schema = yup.object({
  student: yup.object({
    firstName: yup.string().required().min(3).label("First Name"),
    lastName: yup.string().required().label("Last Name"),
    email: yup.string().email().required().min(5).label("Email"),
  }),
});

const initialValues = { lastName: "", firstName: "", email: "" };

const {
  values,
  errors,
  defineComponentBinds,
  defineInputBinds,
  handleSubmit,
  isSubmitting,
} = useForm({
  validationSchema: schema,
  initialValues: initialValues,
});

const firstName = defineInputBinds("student.firstName");
const lastName = defineInputBinds("student.lastName");
const email = defineInputBinds("student.email");

const emit = defineEmits(["close"]);

const failValidation = ({ values, errors, results }) => {
  toast.error(error.message, { position: "top-right" });
  console.log(errors);
};

const onSubmit = handleSubmit(async (values) => {
  console.log("values = > ", values);
  try {
    const response = await studentService.create(values.student);
    toast.success("Student created successfully!", { position: "top-right" });
    close();
  } catch (error) {
    toast.error(error.message, { position: "top-right" });
    return console.error(error);
  }
}, failValidation);

const close = () => {
  emit("close");
};
</script>
