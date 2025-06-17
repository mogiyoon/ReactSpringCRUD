import axios from 'axios';
import {Post} from '../components/title-card';

export const fetchGetPosts = async (): Promise<Post[]> => {
  const response = await axios.get<Post[]>('http://localhost:8080/posts');
  console.log("response: ", response);
  return response.data
}

export const fetchCreatePost = async (newPost: Post): Promise<Post> => {
  const response = await axios.post<Post>('http://localhost:8080/posts', newPost);
  return response.data;
};