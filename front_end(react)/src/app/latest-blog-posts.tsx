"use client";

import React, { useEffect, useState } from "react";
import { Typography } from "@material-tailwind/react";
import TitleCard, {Post} from "@/components/title-card";
import { fetchGetPosts } from "@/services/post-service";

export function LatestBlogPosts() {
  const [posts, setPosts] = useState<Post[]>([]);

  useEffect(() => {
    fetchGetPosts().then(setPosts).catch(console.error);
  }, [])

  return (
    <section className="py-20 px-8">
      <div className="container mx-auto mb-12">
        <Typography variant="h3" color="blue-gray">
          Check my latest blog posts
        </Typography>
      </div>
      <div className="container mx-auto grid grid-cols-1 gap-10 md:grid-cols-2 lg:grid-cols-4">
        {posts.map((props, idx) => (
          <TitleCard key={idx} {...props} />
        ))}
      </div>
    </section>
  );
}


export default LatestBlogPosts;