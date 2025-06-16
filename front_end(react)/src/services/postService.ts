import axios from 'axios';
import {Post} from '../components/title-card';

export const fetchPosts = async (): Promise<Post[]> => {
  const response = await axios.get<Post[]>('http://localhost:8080/posts');
  return response.data
}