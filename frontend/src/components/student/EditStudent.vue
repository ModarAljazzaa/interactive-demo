<template>
  <div class="bg-white w-full max-w-md p-4 rounded-lg shadow-lg">
    <div class="text-3xl font-semibold mb-6">Edit Student</div>
    <Form @submit="onSubmit" :validation-schema="schema">
      <div class="mb-4">
        <label for="firstName" class="block text-gray-600">First Name:</label>
        <Field
          name="firstName"
          v-model="student.firstName"
          type="text"
          id="firstname"
          class="w-full border rounded px-3 py-2"
        />
        <ErrorMessage class="text-red-500 mt-3" name="firstName" />
      </div>
      <div class="mb-4">
        <label for="lastName" class="block text-gray-600">Last Name:</label>
        <Field
          name="lastName"
          v-model="student.lastName"
          type="text"
          id="lastname"
          class="w-full border rounded px-3 py-2"
        />
        <ErrorMessage class="text-red-500 mt-3" name="lastName" />
      </div>
      <div class="mb-4">
        <label for="email" class="block text-gray-600">Email:</label>
        <Field
          name="email"
          v-model="student.email"
          type="email"
          id="email"
          class="w-full border rounded px-3 py-2"
        />
        <ErrorMessage class="text-red-500 mt-3" name="email" />
      </div>
      <div class="flex justify-between mt-4">
        <button
          type="submit"
          class="rounded-lg bg-green-700 px-4 py-2 text-sm font-medium text-white hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
        >
          Update Student
        </button>
      </div>
    </Form>
  </div>
</template>

<script setup>
import studentService from "../../services/student.service";
import { onMounted, reactive, ref, watch } from "vue";
import * as yup from "yup";
import { useForm } from "vee-validate";
import { useToast } from "vue-toast-notification";
import { useRoute } from "vue-router";

const route = useRoute();
const student = ref({ firstName: "", lastName: "", email: "" });
import { Form, Field, ErrorMessage } from "vee-validate";
const toast = useToast();
const schema = yup.object({
  firstName: yup.string().required().min(3).label("First Name"),
  lastName: yup.string().required().label("Last Name"),
  email: yup.string().email().required().min(5).label("Email"),
});

const initialValues = student.value;

console.log(initialValues);

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

const failValidation = ({ values, errors, results }) => {
  console.log(errors);
  console.log(values);
};

const onSubmit = async (values) => {
  try {
    const response = await studentService.update(student.value.id, values);
    toast.success("Student updated successfully!", { position: "top-right" });
    close();
  } catch (error) {
    toast.error(error.message, { position: "top-right" });
    return console.error(error);
  }
};

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
    console.log(response);
    return (student.value = response);
  } catch (error) {
    toast.error(error.message, {
      position: "top-right",
    });
    return console.error(error);
  }
};
</script>
