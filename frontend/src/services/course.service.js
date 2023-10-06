import axiosInstance from "../axiosConfig";

class CourseService {
  async getAll() {
    try {
      const response = await axiosInstance.get("/courses");
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async get(id) {
    try {
      const response = await axiosInstance.get(`/courses/${id}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async create(data) {
    try {
      const response = await axiosInstance.post("/courses", data);
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async update(id, data) {
    try {
      const response = await axiosInstance.put(`/courses/${id}`, data);
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async delete(id) {
    try {
      const response = await axiosInstance.delete(`/courses/${id}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async deleteAll() {
    try {
      const response = await axiosInstance.delete(`/courses`);
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async findByTitle(title) {
    try {
      const response = await axiosInstance.get(`/courses?title=${title}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
}

export default new CourseService();
