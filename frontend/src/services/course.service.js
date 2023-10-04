import axiosInstance from "../axiosConfig";

class CourseService {
  getAll() {
    return axiosInstance.get("/courses");
  }

  get(id) {
    return axiosInstance.get(`/courses/${id}`);
  }

  create(data) {
    return axiosInstance.post("/courses", data);
  }

  update(id, data) {
    return axiosInstance.put(`/courses/${id}`, data);
  }

  delete(id) {
    return axiosInstance.delete(`/courses/${id}`);
  }

  deleteAll() {
    return axiosInstance.delete(`/courses`);
  }

  findByTitle(title) {
    return http.get(`/courses?title=${title}`);
  }
}

export default new CourseService();
