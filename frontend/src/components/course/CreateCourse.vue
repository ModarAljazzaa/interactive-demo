<template>
  <div class="fixed inset-0 flex items-center justify-center z-50">
    <div class="modal-container">
      <div class="bg-white w-full max-w-md p-4 rounded-lg shadow-lg">
        <div class="text-3xl font-semibold mb-6">Create Course</div>
        <form @submit.prevent="onSubmit">
          <div class="mb-4">
            <label for="title" class="block text-gray-600">Title:</label>
            <input
              v-bind="title"
              type="text"
              id="title"
              class="w-full border rounded px-3 py-2"
            />
            <span class="text-red-600 mt-3"> {{ errors["course.title"] }}</span>
          </div>
          <div class="mb-4">
            <label for="description" class="block text-gray-600"
              >Description:</label
            >
            <input
              v-bind="description"
              type="text"
              id="description"
              class="w-full border rounded px-3 py-2"
            />
            <span class="text-red-500 mt-3">
              {{ errors["course.description"] }}</span
            >
          </div>

          <div class="flex justify-between mt-4">
            <button
              type="submit"
              class="rounded-lg bg-green-700 px-4 py-2 text-sm font-medium text-white hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800"
            >
              Create Course
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
import courseService from "../../services/course.service";
import { reactive, ref } from "vue";
import * as yup from "yup";
import { useField, useForm } from "vee-validate";
import { useToast } from "vue-toast-notification";

// const course = ref({ lastname: "", firstname: "", email: "" });
const toast = useToast();
const schema = yup.object({
  course: yup.object({
    title: yup.string().required().min(4).label("Title"),
    description: yup.string().required().min(4).label("Description"),
  }),
});

const initialValues = { description: "", title: "", email: "" };

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

const title = defineInputBinds("course.title");
const description = defineInputBinds("course.description");

const emit = defineEmits(["close"]);

const failValidation = ({ values, errors, results }) => {
  toast.error(error.message, { position: "top-right" });
  console.log(errors);
};

const onSubmit = handleSubmit(async (values) => {
  console.log("values = > ", values);
  try {
    const response = await courseService.create(values.course);
    toast.success("Course created successfully!", { position: "top-right" });
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
