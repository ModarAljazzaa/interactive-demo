import { createWebHistory, createRouter } from "vue-router";

import {
  CourseList,
  Course,
  StudentList,
  Student,
  CreateStudent,
} from "./components";

const routes = [
  {
    path: "/",
    alias: "/courses",
    name: "courses-list",
    component: CourseList,
  },
  {
    path: "/courses/:id",
    name: "course-details",
    component: Course,
  },

  {
    path: "/students",
    name: "students-list",
    component: StudentList,
  },
  {
    path: "/students/:id",
    name: "student-details",
    component: Student,
  },
  {
    path: "/create-student",
    name: "create-student",
    component: CreateStudent,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
