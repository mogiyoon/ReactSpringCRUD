"use client";

import { Button, Typography } from "@material-tailwind/react";
import { fetchCreatePost } from "@/services/post-service";
import { useState } from "react";

export function CreateBlogPosts() {
  const [post, setPost] = useState({
    title: "",
    contents: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setPost((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <section className="py-10 px-8">
      <div className="container mx-auto mb-2">
        <Typography variant="h3" color="blue-gray">
          Create blog posts
        </Typography>
        <div className="sm:col-span-4">
          <label htmlFor="title" className="block text-sm/6 font-medium text-gray-900">
            Title
          </label>
          <div className="mt-2">
            <div className="flex items-center rounded-md bg-white pl-3 outline-1 -outline-offset-1 outline-gray-300 focus-within:outline-2 focus-within:-outline-offset-2 focus-within:outline-indigo-600 border-2">
              <input
                id="title"
                name="title"
                type="text"
                value={post.title}
                onChange={handleChange}
                placeholder="Title"
                className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
              />
            </div>
          </div>
        </div>

        <div className="col-span-full">
          <label htmlFor="contents" className="block text-sm/6 font-medium text-gray-900">
            Contents
          </label>
          <div className="mt-2">
            <textarea
              id="contents"
              name="contents"
              rows={3}
              value={post.contents}
              onChange={handleChange}
              placeholder="Contents"
              className="block w-full rounded-md bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 focus:outline-2 focus:-outline-offset-2 focus:outline-indigo-600 sm:text-sm/6 border-2"
            />
          </div>
          <p className="mt-3 text-sm/6 text-gray-600">Write a few sentences about yourself.</p>
        </div>
        <Button color="gray" onClick={() => fetchCreatePost(post)}>create</Button>
      </div>
    </section>
  );
}


export default CreateBlogPosts;