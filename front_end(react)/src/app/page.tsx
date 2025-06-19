// components

import { Footer, Navbar } from "@/components";
import Hero from "./hero";
import CreateBlogPosts from "./create-blog-posts";
import LatestBlogPosts from "./latest-blog-posts";
import { AuthProvider } from "@/context/AuthContext";

// sections


export default function Campaign() {
  return (
    <AuthProvider>
      <Navbar />
      <Hero />
      <CreateBlogPosts />
      <LatestBlogPosts />
      <Footer />
    </AuthProvider>
  );
}
