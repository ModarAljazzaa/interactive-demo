import axiosInstance from "../axiosConfig";

class StudentService {
  async getAll() {
    try {
      const response = await axiosInstance.get("/students");
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async get(id) {
    try {
      const response = await axiosInstance.get(`/students/${id}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async create(data) {
    try {
      const response = await axiosInstance.post("/students", data);
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async update(id, data) {
    try {
      const response = await axiosInstance.put(`/students/${id}`, data);
      return response.data;
    } catch (error) {
      throw error;
    }
  }

  async delete(id) {
    try {
      await axiosInstance.delete(`/students/${id}`);
    } catch (error) {
      throw error;
    }
  }

  async deleteAll() {
    try {
      await axiosInstance.delete(`/students`);
    } catch (error) {
      throw error;
    }
  }

  async findByTitle(title) {
    try {
      const response = await axiosInstance.get(`/students?title=${title}`);
      return response.data;
    } catch (error) {
      throw error;
    }
  }
}

export default new StudentService();
