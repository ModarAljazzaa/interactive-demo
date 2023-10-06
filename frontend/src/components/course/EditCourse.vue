<template>
  <div class="bg-white w-full max-w-md p-4 rounded-lg shadow-lg">
    <div class="text-3xl font-semibold mb-6">Edit Course</div>
    <Form @submit="onSubmit" :validation-schema="schema">
      <div class="mb-4">
        <label for="title" class="block text-gray-600">Title:</label>
        <Field
          name="title"
          v-model="course.title"
          type="text"
          id="firstname"
          class="w-full border rounded px-3 py-2"
        />
        <ErrorMessage class="text-red-500 mt-3" name="title" />
      </div>
      <div class="mb-4">
        <label for="description" class="block text-gray-600"
          >Description:</label
        >
        <Field
          name="description"
          v-model="course.description"
          type="text"
          id="lastname"
          class="w-full border rounded px-3 py-2"
        />
        <ErrorMessage class="text-red-500 mt-3" name="description" />
      </div>
      <div class="flex justify-between mt-4">
        <button
          type="submit"
          class="rounded-lg bg-green-700 px-4 py-2 text-sm font-medium text-white hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
        >
          Update Course
        </button>
      </div>
    </Form>
  </div>
</template>

<script setup>
import courseService from "../../services/course.service";
import { onMounted, reactive, ref, watch } from "vue";
import * as yup from "yup";
import { useForm } from "vee-validate";
import { useToast } from "vue-toast-notification";
import { useRoute } from "vue-router";

const route = useRoute();
const course = ref({ title: "", description: "", email: "" });
import { Form, Field, ErrorMessage } from "vee-validate";
const toast = useToast();
const schema = yup.object({
  title: yup.string().required().min(3).label("Title"),
  description: yup.string().required().label("Description"),
});

const initialValues = course.value;

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
    const response = await courseService.update(course.value.id, values);
    toast.success("Course updated successfully!", { position: "top-right" });
    close();
  } catch (error) {
    toast.error(error.message, { position: "top-right" });
    return console.error(error);
  }
};

onMounted(() => {
  getCourse(route.params.id);
});

watch(
  () => route.params.id,
  async (newId) => {
    course.value = await getCourse(newId);
  }
);

const getCourse = async (id) => {
  try {
    const response = await courseService.get(id);
    console.log(response);
    return (course.value = response);
  } catch (error) {
    toast.error(error.message, {
      position: "top-right",
    });
    return console.error(error);
  }
};
</script>
