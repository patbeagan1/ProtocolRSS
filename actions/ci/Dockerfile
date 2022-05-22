FROM debian:9.5-slim

ADD ../../scripts/ci.sh /ci.sh
RUN chmod +x /ci.sh
ENTRYPOINT ["/ci.sh"]
