// components
import { Navbar, Footer } from "@/components";

// sections
import Hero from "./hero";
import LatestBlogPosts from "./latest-blog-posts";

export default function Campaign() {
  return (
    <>
      <Navbar />
      <Hero />
      <LatestBlogPosts />
      <Footer />
    </>
  );
}
