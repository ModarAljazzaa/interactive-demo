import { createWebHistory, createRouter } from "vue-router";

import {
  CourseList,
  Course,
  EditCourse,
  StudentList,
  Student,
  EditStudent,
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
    path: "/courses/edit/:id",
    name: "edit-course-details",
    component: EditCourse,
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
    path: "/students/edit/:id",
    name: "edit-student-details",
    component: EditStudent,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
