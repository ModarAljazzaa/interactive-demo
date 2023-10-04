import { createWebHistory, createRouter } from "vue-router";

const routes = [
  {
    path: "/",
    alias: "/courses",
    name: "courses-list",
    component: () => import("./components/course/CourseList.vue"),
  },
  {
    path: "/courses/:id",
    name: "course-details",
    component: () => import("./components/course/Course.vue"),
  },

  {
    path: "/students",
    name: "students-list",
    component: () => import("./components/student/StudentList.vue"),
  },
  {
    path: "/students/:id",
    name: "student-details",
    component: () => import("./components/student/Student.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
