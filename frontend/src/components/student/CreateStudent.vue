<template>
  <div class="max-w-md mx-auto mt-10 p-4">
    <form @submit.prevent="submitForm">
      <div class="mb-4">
        <label
          for="firstName"
          class="block text-gray-600 text-sm font-semibold mb-2"
          >First Name:</label
        >
        <input
          v-model="student.firstName"
          v-validate="'required|email|unique'"
          type="text"
          id="firstName"
          name="firstName"
          class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:border-blue-500"
          required
        />
        <span class="text-red-500 text-sm">{{ errors.firstName }}</span>
      </div>
      <div class="mb-4">
        <label
          for="lastName"
          class="block text-gray-600 text-sm font-semibold mb-2"
          >Last Name:</label
        >
        <input
          v-model="student.lastName"
          type="text"
          id="lastName"
          name="lastName"
          class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:border-blue-500"
          required
        />
        <span class="text-red-500 text-sm">{{ errors.lastName }}</span>
      </div>
      <div class="mb-4">
        <label
          for="email"
          class="block text-gray-600 text-sm font-semibold mb-2"
          >Email:</label
        >
        <input
          v-model="student.email"
          type="email"
          id="email"
          name="email"
          class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:border-blue-500"
          required
        />
        <span class="text-red-500 text-sm">{{ errors.email }}</span>
      </div>
      <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-lg">
        Submit
      </button>
    </form>
  </div>
</template>

<script>
import { ref } from "vue";
// import { useVuelidate } from "@vuelidate/core";
// import { required, email } from "@vuelidate/validators";

export default {
  setup() {
    const student = ref({
      firstName: "",
      lastName: "",
      email: "",
    });

    // const rules = {
    //   student: {
    //     firstName: { required },
    //     lastName: { required },
    //     email: { required, email },
    //   },
    // };

    const { handleSubmit, errors } = useVuelidate(rules, student);

    const submitForm = handleSubmit(async () => {
      // Handle form submission here
      console.log(student.value);
      // You can send the data to your API or perform other actions here
    });

    return {
      student,
      submitForm,
      errors,
    };
  },
};
</script>
