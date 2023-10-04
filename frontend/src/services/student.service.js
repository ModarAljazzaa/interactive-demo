import axiosInstance from "../axiosConfig";

class StudentService {
  getAll() {
    return axiosInstance.get("/students");
  }

  get(id) {
    return axiosInstance.get(`/students/${id}`);
  }

  create(data) {
    return axiosInstance.post("/students", data);
  }

  update(id, data) {
    return axiosInstance.put(`/students/${id}`, data);
  }

  delete(id) {
    return axiosInstance.delete(`/students/${id}`);
  }

  deleteAll() {
    return axiosInstance.delete(`/students`);
  }

  findByTitle(title) {
    return http.get(`/students?title=${title}`);
  }
}

export default new StudentService();
