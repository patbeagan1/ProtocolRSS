FROM debian:9.5-slim

ADD ./ci.sh /ci.sh
RUN chmod +x /ci.sh
ENTRYPOINT ["/ci.sh"]
