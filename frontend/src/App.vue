<template>
  <div id="app">
    <Layout>
      <template #header>
        <Header />
      </template>
      <template #main>
        <div class="container mx-auto mb-3">
          <router-view />
        </div>
      </template>
      <template #footer>
        <Footer />
      </template>
    </Layout>
  </div>
</template>

<script>
import "vue-toast-notification/dist/theme-sugar.css";
import StudentList from "./components/student/StudentList.vue";
import Header from "./layouts/Header.vue";
import Footer from "./layouts/Footer.vue";
import Layout from "./layouts/Layout.vue";

export default {
  name: "App",
  components: {
    Layout,
    StudentList,
    Header,
    Footer,
  },
};
</script>
