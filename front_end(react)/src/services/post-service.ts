import axios from 'axios';
import { Post } from "@/model/post-model";

export const fetchGetPosts = async (): Promise<Post[]> => {
  const response = await axios.get<Post[]>('http://localhost:8080/api/posts');
  return response.data
}

export const fetchCreatePost = async (newPost: Post): Promise<Post> => {
  const response = await axios.post<Post>('http://localhost:8080/api/posts', newPost);
  return response.data;
};