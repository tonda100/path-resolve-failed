package net.tonda100.prf;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;


@Path("/books")
public class BookResource {

    private static final Map<String, Book> BOOKS = Map.of(
            "a", new Book("Book A", "John Doe"),
            "b", new Book("Book B", "Jane Doe")
    );

    @Inject
    Logger log;

    @GET
    public Response getBooks() {
        return Response.ok(BOOKS.values()).build();
    }

    @Path("/{id}")
    @GET
    public Response getBook(@PathParam("id") String id, @Context HttpHeaders headers) {
        log.info(headers.getRequestHeaders().toString());
        if (!BOOKS.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(BOOKS.get(id)).build();
    }

    @Path("/image")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getBook(@Context HttpHeaders headers) {
        log.info(headers.getRequestHeaders().toString());
        return Response.ok("image" + System.currentTimeMillis())
                //.header("Content-Type", MediaType.APPLICATION_OCTET_STREAM)
                .header("Cache-Control", "max-age=31536000")
                .header("Content-Disposition", "attachment; filename=book.txt")
                .build();
    }
}
