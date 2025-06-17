// components

import { Footer, Navbar } from "@/components";
import Hero from "./hero";
import CreateBlogPosts from "./create-blog-posts";
import LatestBlogPosts from "./latest-blog-posts";

// sections


export default function Campaign() {
  return (
    <>
      <Navbar />
      <Hero />
      <CreateBlogPosts />
      <LatestBlogPosts />
      <Footer />
    </>
  );
}
