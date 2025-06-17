import React from "react";
import {
  Button,
  Typography,
  Card,
  CardBody,
} from "@material-tailwind/react";

import { ArrowRightIcon } from "@heroicons/react/24/outline";
import { Post } from "@/model/post-model";

export function TitleCard({ id, title, contents }: Post) {
  return (
    <Card color="transparent" shadow={false}>
      <CardBody className="p-0">
        <a
          href="#"
          className="text-blue-gray-900 transition-colors hover:text-gray-800"
        >
          <Typography variant="h5" className="mb-2">
            {title}
          </Typography>
        </a>
        <Typography className="mb-3 font-normal !text-gray-500">
          {contents}
        </Typography>
        <Typography className="mb-3 font-normal !text-gray-500">
          {id}
        </Typography>
        <Button variant="text" color="gray" className="flex items-center gap-2">
          read more
          <ArrowRightIcon
            strokeWidth={3}
            className="h-3.5 w-3.5 text-gray-900"
          />
        </Button>
      </CardBody>
    </Card>
  );
}

export default TitleCard;